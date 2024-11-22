package umc.spring.web.dto;

import lombok.Getter;
import lombok.NonNull;
import umc.spring.validation.annotation.ExistStores;

import java.time.LocalDate;

public class MissionRequestDTO {

    // 가게에 미션 추가
    @Getter
    public static class CreateMissionDTO {
        @ExistStores
        Long storeId;
        @NonNull
        Integer reward;
        @NonNull
        String missionSpec;
        @NonNull
        LocalDate deadline;

    }
}
