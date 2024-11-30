package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;
        switch (request.getGender()) {
            case 1: gender = Gender.MALE; break;
            case 2: gender = Gender.FEMALE; break;
            case 3: gender = Gender.NONE; break;
        }

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())   // 추가된 코드
                .password(request.getPassword())   // 추가된 코드
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .role(request.getRole())   // 추가된 코드
                .memberPreferList(new ArrayList<>())
                .build();
    }

    // 내가 작성한 리뷰 목록 조회
    public static MemberResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static MemberResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<MemberResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::reviewPreViewDTO).collect(Collectors.toList());
        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    // 내가 진행중/진행완료인 미션 목록
    public static MissionResponseDTO.MyMissionPreViewDTO missionPreViewDTO(MissionResponseDTO.MyMissionPreViewDTO mission){

        return MissionResponseDTO.MyMissionPreViewDTO.builder()
                .storeName(mission.getStoreName())
                .status(mission.getStatus())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .build();
    }
    public static MissionResponseDTO.MyMissionPreViewListDTO missionPreViewListDTO(Page<MissionResponseDTO.MyMissionPreViewDTO> missionList){
        List<MissionResponseDTO.MyMissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MemberConverter::missionPreViewDTO).collect(Collectors.toList());
        return MissionResponseDTO.MyMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}