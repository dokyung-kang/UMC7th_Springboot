package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPaylaod.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionRestController {

    private final MissionQueryService missionQueryService;
    private final MemberMissionQueryService memberMissionQueryService;

    // 가게에 미션 추가
    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.CreateMissionResultDTO> createMission(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request){
        Mission mission = missionQueryService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionResultDTO(mission));
    }

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDTO.UpdateMemberMissionResultDTO> updateMemberMission(@RequestBody @Valid MissionRequestDTO.UpdateMemberMissionDTO request){
        MemberMission memberMission = memberMissionQueryService.updateMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateMemberMissionResultDTO(memberMission));
    }


    // 진행중인 미션 진행 완료로 바꾸기
    @GetMapping("/{membermissionId}/complete")
    @Operation(summary = "진행중인 미션 진행 완료로 바꾸기 API",description = "진행중인 미션 진행 완료로 바꾸기 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "membermissionId", description = "진행중인 미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.CompleteMemberMissionResultDTO> completeMemberMission(@PathVariable(name = "membermissionId") Long membermissionId){
        MemberMission memberMission = memberMissionQueryService.completeMemberMission(membermissionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toCompleteMemberMissionResultDTO(memberMission));
    }
}
