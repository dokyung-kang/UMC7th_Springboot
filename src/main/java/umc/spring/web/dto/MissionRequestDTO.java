package umc.spring.web.dto;

import lombok.Getter;
import lombok.NonNull;
import umc.spring.validation.annotation.ExistStores;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.AlreadyExistMemberMission;
import umc.spring.validation.annotation.ExistMissions;

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

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @Getter
    public static class UpdateMemberMissionDTO{
        @ExistMissions
        @AlreadyExistMemberMission
        Long missionId;
    }
}
