package umc.spring.repository.MissionRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;


public interface MissionRepositoryCustom {
    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    Page<MissionResponseDTO.MissionStatusDTO> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status, Long currentMissionId, Pageable pageable);

    // 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록)
    Page<MissionResponseDTO.HomeMissionDTO> findMissionByMemberIdAndRegion(Long memberId, Long regionId, Long currentMissionId, Pageable pageable);

    // 내가 진행중인 미션 목록
    Page<MissionResponseDTO.MyMissionPreViewDTO> findMissionsByMemberIdAndStatus(Member member, Integer status, Pageable pageable);
}