package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.apiPaylaod.exception.handler.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.service.FoodCategoryService.FoodCategoryService;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final FoodCategoryService foodCategoryService;
    private final MissionRepository missionRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);

        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(foodCategoryService::findById)
                .collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    // 내가 작성한 리뷰 목록 조회
    public Page<Review> getReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId).get();
        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }

    // 내가 진행중인 미션 목록
    public Page<MissionResponseDTO.MyMissionPreViewDTO> getMissionList(Long memberId, Integer status, Integer page){

        Member member = memberRepository.findById(memberId).get();
        Page<MissionResponseDTO.MyMissionPreViewDTO> MemberPage = missionRepository.findMissionsByMemberIdAndStatus(member, status, PageRequest.of(page, 10));
        return MemberPage;
    }
}