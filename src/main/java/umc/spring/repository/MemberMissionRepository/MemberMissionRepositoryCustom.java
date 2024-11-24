package umc.spring.repository.MemberMissionRepository;




public interface MemberMissionRepositoryCustom {

    // 이미 도전중/도전완료 미션인지 유무
    boolean existsByMissionIdAndMemberId(Long missionId, Long memberId);

    // 진행중인 미션 진행 완료로 바꾸기
    void updateStatusToComplete(Long memberMissionId);
}