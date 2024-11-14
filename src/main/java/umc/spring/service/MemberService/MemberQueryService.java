package umc.spring.service.MemberService;



import umc.spring.domain.Member;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

    MemberResponseDTO.MemberInfoDTO findMemberByMemberIdService(Long memberId);


}