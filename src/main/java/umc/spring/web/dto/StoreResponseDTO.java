package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDTO {
    // 특정 지역에 가게 추가
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreResultDTO{
        Long storeId;
        LocalDateTime createdAt;
    }
}
