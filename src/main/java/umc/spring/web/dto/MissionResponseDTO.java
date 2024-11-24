package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MissionResponseDTO {

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionStatusDTO{
        private Long missionId;
        private String storeName;
        private Integer reward;
        private String missionSpec;
        private MissionStatus status;

        public static MissionStatusDTO from(Mission mission, String storeName, Integer reward, String missionSpec, MissionStatus status) {
            return MissionStatusDTO.builder()
                    .missionId(mission.getId())
                    .storeName(storeName)
                    .reward(reward)
                    .missionSpec(missionSpec)
                    .status(status)
                    .build();
        }

        @Override
        public String toString() {
            return "MissionStatusDTO{" +
                    "missionId=" + missionId +
                    ", storeName='" + storeName + '\'' +
                    ", reward=" + reward +
                    ", missionSpec='" + missionSpec + '\'' +
                    ", status=" + status +
                    '}';
        }

    }

    // 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeMissionDTO{
        private Long missionId;
        private String storeName;
        private Integer reward;
        private String missionSpec;
        private Long daysLeft;

        public static HomeMissionDTO from(Mission mission, String storeName, Integer reward, String missionSpec, Long daysLeft) {
            return HomeMissionDTO.builder()
                    .missionId(mission.getId())
                    .storeName(storeName)
                    .reward(reward)
                    .missionSpec(missionSpec)
                    .daysLeft(daysLeft)
                    .build();
        }

        @Override
        public String toString() {
            return "HomeMissionDTO{" +
                    "missionId=" + missionId +
                    ", storeName='" + storeName + '\'' +
                    ", reward=" + reward +
                    ", missionSpec='" + missionSpec + '\'' +
                    ", daysLeft=" + daysLeft +
                    '}';
        }

    }

    // 가게에 미션 추가
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResultDTO{
        Long missionId;
        LocalDateTime createdAt;
    }
  
    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateMemberMissionResultDTO{
        Long memberMissionId;
        LocalDateTime createdAt;
    }

    // 특정 가게의 미션 목록
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        List<MissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        Integer reward;
        String missionSpec;
        LocalDate deadline;
        LocalDate createdAt;
    }
}
