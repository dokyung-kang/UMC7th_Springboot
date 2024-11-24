package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);

    // 내가 작성한 리뷰 목록 조회
    Page<Review> getReviewList(Long memberId, Integer page);

    // 내가 진행중인 미션 목록
    Page<MissionResponseDTO.MyMissionPreViewDTO> getMissionList(Long memberId, Integer status, Integer page);
}