package umc.spring.service.ReviewService;



import umc.spring.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {

    Optional<Review> findReview(Long id);

    // 리뷰 작성하는 쿼리
    Long createReviewService(Long memberId, Long storeId, String body, Float score);
}