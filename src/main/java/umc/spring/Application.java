package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.MemberResponseDTO;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);
			MissionQueryService missionService = context.getBean(MissionQueryService.class);
			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);
			MemberQueryService memberService = context.getBean(MemberQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;
			Long memberId = 2L;
			MissionStatus status = MissionStatus.COMPLETE;
			Long currentMissionId = 10L;
			String body = "오 맛나다!!";
			Long regionId = 1L;
			Long storeId = 1L;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			// 실습
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("memberId: " + memberId);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);

			// 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
//			System.out.println("Executing findMissionsByMemberIdAndStatus with parameters:");
//			System.out.println("memberId: " + memberId);
//			System.out.println("status: " + status);
//
//			missionService.findMissionByMemberIdAndStatusService(memberId, status, currentMissionId)
//					.forEach(System.out::println);


			// 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록)
			System.out.println("Executing findMissionByMemberIdAndRegionService with parameters:");
			System.out.println("memberId: " + memberId);
			System.out.println("status: " + status);
			System.out.println("status: " + status);


		};
	}
}
