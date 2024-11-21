package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {
    public static ReviewResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO request, Store store){

        return Review.builder()
                .store(store)
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .reviewImageList(new ArrayList<>())
                .build();
    }
}
