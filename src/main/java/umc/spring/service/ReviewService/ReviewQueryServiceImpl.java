package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.apiPaylaod.exception.handler.ReviewHandler;
import umc.spring.aws.AmazonS3Manager;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.*;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewImageRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UuidRepository;
import umc.spring.web.dto.ReviewRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Optional<Review> findReview(Long id) {
        return reviewRepository.findById(id);
    }

    // 리뷰 작성하는 쿼리
    @Override
    public void createReviewService(Long memberId, Long storeId, String body, Float score) {
        reviewRepository.createReview(memberId, storeId, body, score);
    }

    // 리뷰 작성
    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.CreateReviewDTO request, MultipartFile reviewPicture) {

        Review newReview = ReviewConverter.toReview(request);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);


        // 가게 설정
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.STORE_NOT_FOUND));
        newReview.setStore(store);

        // 유저 하드 코딩
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.MEMBER_NOT_FOUND));
        System.out.println("member " + member);
        newReview.setMember(member);

//        // 리뷰 사진 링크
//        List<ReviewImage> reviewImageList = request.getReviewImageList().stream()
//                .map(imageUrl -> ReviewImage.builder()
//                        .imageUrl(imageUrl)
//                        .review(newReview)
//                        .build())
//                .toList();
//        newReview.getReviewImageList().addAll(reviewImageList);

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, newReview));
        return reviewRepository.save(newReview);
    }
}