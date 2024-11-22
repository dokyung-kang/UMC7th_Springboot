package umc.spring.service.MissionService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {

    Optional<Mission> findMission(Long id);

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    Page<MissionResponseDTO.MissionStatusDTO> findMissionByMemberIdAndStatusService(Long memberId, MissionStatus status, Long currentMissionId, Pageable pageable);

    // 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록)
    Page<MissionResponseDTO.HomeMissionDTO> findMissionByMemberIdAndRegionService(Long memberId, Long regionId, Long currentMissionId, Pageable pageable);

    // 가게에 미션 추가
    Mission createMission(MissionRequestDTO.CreateMissionDTO request);
}