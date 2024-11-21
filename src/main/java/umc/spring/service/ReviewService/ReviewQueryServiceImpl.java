package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.apiPaylaod.exception.handler.ReviewHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

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
    public Review createReview(ReviewRequestDTO.CreateReviewDTO request) {

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toReview(request, store);

        // 유저 하드 코딩
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.MEMBER_NOT_FOUND));
        System.out.println("member " + member);
        newReview.setMember(member);

        List<ReviewImage> reviewImageList = request.getReviewImageList().stream()
                .map(imageUrl -> ReviewImage.builder()
                        .imageUrl(imageUrl)
                        .review(newReview)
                        .build())
                .toList();

        newReview.getReviewImageList().addAll(reviewImageList);

        return reviewRepository.save(newReview);
    }
}