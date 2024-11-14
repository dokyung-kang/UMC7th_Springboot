package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberInfoDTO{
        private Long memberId;
        private String name;
        private String email;
        private Integer point;
        private String phoneNum;

        public static MemberInfoDTO from(Long memberId, String name, String email, Integer point, String phoneNum) {
            return MemberInfoDTO.builder()
                    .memberId(memberId)
                    .name(name)
                    .email(email)
                    .point(point)
                    .phoneNum(phoneNum)
                    .build();
        }

        @Override
        public String toString() {
            return "member{" +
                    "id=" + memberId +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", point='" + point + '\'' +
                    ", phoneNum=" + phoneNum +
                    '}';
        }

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }
}
