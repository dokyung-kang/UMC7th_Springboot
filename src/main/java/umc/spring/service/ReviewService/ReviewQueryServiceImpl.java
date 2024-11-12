package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.repository.ReviewRepository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> findReview(Long id) {
        return reviewRepository.findById(id);
    }

    // 리뷰 작성하는 쿼리
    @Override
    public void createReviewService(Long memberId, Long storeId, String body, Float score) {
        reviewRepository.createReview(memberId, storeId, body, score);
    }
}