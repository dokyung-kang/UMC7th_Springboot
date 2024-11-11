package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.Date;

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
}
