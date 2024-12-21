package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.Store;
import umc.spring.validation.annotation.ExistStores;

import java.util.List;

public class ReviewRequestDTO {

    // 리뷰 작성
    @Getter
    public static class CreateReviewDTO {
        @ExistStores
        Long storeId;
        @NotNull
        Float score;
        @NotNull
        String title;
        @NotNull
        String body;
//        List<String> reviewImageList;
    }
}
