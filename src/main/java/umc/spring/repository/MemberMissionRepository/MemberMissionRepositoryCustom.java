package umc.spring.repository.MemberMissionRepository;




public interface MemberMissionRepositoryCustom {

    // 이미 도전중/도전완료 미션인지 유무
    boolean existsByMissionIdAndMemberId(Long missionId, Long memberId);
}