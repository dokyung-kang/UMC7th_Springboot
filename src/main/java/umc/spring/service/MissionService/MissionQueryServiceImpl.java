package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.apiPaylaod.exception.handler.MissionHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    @Override
    public Page<MissionResponseDTO.MissionStatusDTO> findMissionByMemberIdAndStatusService(Long memberId, MissionStatus status, Long currentMissionId, Pageable pageable) {
        Page<MissionResponseDTO.MissionStatusDTO> filteredMissions = missionRepository.findMissionByMemberIdAndStatus(memberId, status, currentMissionId, pageable);

        filteredMissions.forEach(mission -> System.out.println("Mission: " + mission));

        return filteredMissions;
    }

    // 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록)
    @Override
    public Page<MissionResponseDTO.HomeMissionDTO> findMissionByMemberIdAndRegionService(Long memberId, Long regionId, Long currentMissionId, Pageable pageable) {
        Page<MissionResponseDTO.HomeMissionDTO> filteredMissions = missionRepository.findMissionByMemberIdAndRegion(memberId, regionId, currentMissionId, pageable);

        filteredMissions.forEach(mission -> System.out.println("Mission: " + mission));

        return filteredMissions;
    }

    // 가게에 미션 추가
    @Override
    public Mission createMission(MissionRequestDTO.CreateMissionDTO request){

        Mission newMission = MissionConverter.toMission(request);

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_NOT_FOUND));
        newMission.setStore(store);

        return missionRepository.save(newMission);
    }
}