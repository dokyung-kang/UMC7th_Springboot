package umc.spring.repository.MemberRepository;



import umc.spring.domain.Member;
import umc.spring.web.dto.MemberResponseDTO;


public interface MemberRepositoryCustom {
    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    MemberResponseDTO.MemberInfoDTO findMemberByMemberId(Long memberId);

}