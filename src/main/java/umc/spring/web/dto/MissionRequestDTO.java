package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.AlreadyExistMemberMission;
import umc.spring.validation.annotation.ExistMissions;

public class MissionRequestDTO {

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @Getter
    public static class UpdateMemberMissionDTO{
        @ExistMissions
        @AlreadyExistMemberMission
        Long missionId;
    }
}
