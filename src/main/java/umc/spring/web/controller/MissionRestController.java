package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPaylaod.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionRestController {

    private final MemberMissionQueryService memberMissionQueryService;

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDTO.UpdateMemberMissionResultDTO> updateMemberMission(@RequestBody @Valid MissionRequestDTO.UpdateMemberMissionDTO request){
        MemberMission memberMission = memberMissionQueryService.updateMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateMemberMissionResultDTO(memberMission));
    }

}
