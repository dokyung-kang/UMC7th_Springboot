package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPaylaod.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewRestController {
    private final ReviewQueryService reviewQueryService;

    // 리뷰 작성
    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(@Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
                                                                                 @RequestPart("request") @Valid ReviewRequestDTO.CreateReviewDTO request,
                                                                             @RequestPart("reviewPicture")MultipartFile reviewPicture){
        Review review = reviewQueryService.createReview(request, reviewPicture);
        return ApiResponse.onSuccess(ReviewConverter.toCreateReviewResultDTO(review));
    }
}
