package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import umc.spring.validation.annotation.ExistRegion;

public class StoreRequestDTO {
    // 특정 지역에 가게 추가
    @Getter
    public static class CreateStoreDTO {
        @ExistRegion
        Long regionId;
        @NotBlank
        String name;
        @NotBlank
        String address;
    }
}
