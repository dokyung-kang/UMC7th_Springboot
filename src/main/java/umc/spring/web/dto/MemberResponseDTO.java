package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    // 내가 작성한 리뷰 목록 조회
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<MemberResponseDTO.ReviewPreViewDTO> reviewList;
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
    public static class ReviewPreViewDTO {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }
}
