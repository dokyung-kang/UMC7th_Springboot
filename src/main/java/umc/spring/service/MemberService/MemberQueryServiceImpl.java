package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    @Override
    public MemberResponseDTO.MemberInfoDTO findMemberByMemberIdService(Long memberId) {

        return memberRepository.findMemberByMemberId(memberId);
    }
}