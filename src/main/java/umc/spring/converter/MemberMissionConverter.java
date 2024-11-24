package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

// 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
public class MemberMissionConverter {
    public static MissionResponseDTO.UpdateMemberMissionResultDTO toUpdateMemberMissionResultDTO(MemberMission memberMission){
        return MissionResponseDTO.UpdateMemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(MissionRequestDTO.UpdateMemberMissionDTO request){

        MissionStatus status = MissionStatus.CHALLENGING;

        return MemberMission.builder()
                .status(status)
                .build();
    }

    // 진행중인 미션 진행 완료로 바꾸기
    public static MissionResponseDTO.CompleteMemberMissionResultDTO toCompleteMemberMissionResultDTO(MemberMission memberMission){
        return MissionResponseDTO.CompleteMemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
