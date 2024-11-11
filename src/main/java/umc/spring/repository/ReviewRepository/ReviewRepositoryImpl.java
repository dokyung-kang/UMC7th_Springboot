package umc.spring.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.*;


@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    private final QMember member = QMember.member;
    private final QStore store = QStore.store;

    // 리뷰 작성하는 쿼리
    @Transactional
    @Override
    public Long createReview(Long memberId, Long storeId, String body, Float score) {
        Member memberEntity = jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))
                .fetchOne();

        Store storeEntity = jpaQueryFactory
                .selectFrom(store)
                .where(store.id.eq(storeId))
                .fetchOne();

        Review review = Review.builder()
                .member(memberEntity)
                .store(storeEntity)
                .body(body)
                .score(score)
                .build();

        entityManager.persist(review);

        return review.getId();
    }
}