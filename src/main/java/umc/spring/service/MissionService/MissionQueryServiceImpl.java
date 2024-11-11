package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    @Override
    public List<MissionResponseDTO.MissionStatusDTO> findMissionByMemberIdAndStatusService(Long memberId, MissionStatus status, Long currentMissionId) {
        List<MissionResponseDTO.MissionStatusDTO> filteredMissions = missionRepository.findMissionByMemberIdAndStatus(memberId, status, currentMissionId);

        filteredMissions.forEach(mission -> System.out.println("Mission: " + mission));

        return filteredMissions;
    }

    // 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록)
    @Override
    public List<MissionResponseDTO.HomeMissionDTO> findMissionByMemberIdAndRegionService(Long memberId, Long regionId, Long currentMissionId) {
        List<MissionResponseDTO.HomeMissionDTO> filteredMissions = missionRepository.findMissionByMemberIdAndRegion(memberId, regionId, currentMissionId);

        filteredMissions.forEach(mission -> System.out.println("Mission: " + mission));

        return filteredMissions;
    }
}