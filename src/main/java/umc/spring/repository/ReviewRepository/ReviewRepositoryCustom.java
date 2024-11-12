package umc.spring.repository.ReviewRepository;


import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepositoryCustom {
    // 리뷰 작성하는 쿼리
    @Transactional(readOnly = false)
    void createReview(Long memberId, Long storeId, String body, Float score);
}