package umc.spring.service.ReviewService;



import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {

    Optional<Review> findReview(Long id);

    // 리뷰 작성하는 쿼리
    void createReviewService(Long memberId, Long storeId, String body, Float score);

    // 리뷰 작성
    Review createReview(ReviewRequestDTO.CreateReviewDTO request);
}