package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.apiPaylaod.exception.handler.MissionHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.web.dto.MissionRequestDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public Optional<MemberMission> findMemberMission(Long id) {
        return memberMissionRepository.findById(id);
    }

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @Override
    public MemberMission updateMemberMission(MissionRequestDTO.UpdateMemberMissionDTO request){

        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(request);

        // missionId -> mission 찾기
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        newMemberMission.setMission(mission);

        // 유저 하드 코딩
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MEMBER_NOT_FOUND));
        newMemberMission.setMember(member);

        return memberMissionRepository.save(newMemberMission);
    }

    // 이미 도전중/도전완료 미션인지 유무
    @Override
    public boolean existMissionForMember(Long missionId, Long memberId){
        return memberMissionRepository.existsByMissionIdAndMemberId(missionId, memberId);
    }

    // 진행중인 미션 진행 완료로 바꾸기
    @Override
    public MemberMission completeMemberMission(Long memberMissionId){
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        memberMissionRepository.updateStatusToComplete(memberMissionId);

        return memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
    }
}