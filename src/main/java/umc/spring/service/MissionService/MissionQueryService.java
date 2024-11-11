package umc.spring.service.MissionService;


import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {

    Optional<Mission> findMission(Long id);

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    List<MissionResponseDTO.MissionStatusDTO> findMissionByMemberIdAndStatusService(Long memberId, MissionStatus status, Long currentMissionId);

    // 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록)
    List<MissionResponseDTO.HomeMissionDTO> findMissionByMemberIdAndRegionService(Long memberId, Long regionId, Long currentMissionId);

}