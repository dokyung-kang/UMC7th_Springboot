package umc.spring.service.MemberMissionService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MemberMissionQueryService {

    Optional<MemberMission> findMemberMission(Long id);

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    MemberMission updateMemberMission(MissionRequestDTO.UpdateMemberMissionDTO request);

    // 이미 도전중/도전완료 미션인지 유무
    boolean existMissionForMember(Long missionId, Long memberId);

    // 진행중인 미션 진행 완료로 바꾸기
    MemberMission completeMemberMission(Long memberMissionId);
}