package umc.spring.repository.MemberMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    private final QMemberMission memberMission = QMemberMission.memberMission;

    // 이미 도전중/도전완료 미션인지 유무
    @Override
    public boolean existsByMissionIdAndMemberId(Long missionId, Long memberId){
        long count = jpaQueryFactory
                .selectFrom(memberMission)
                .where(memberMission.mission.id.eq(missionId)
                        .and(memberMission.member.id.eq(memberId)))
                .fetchCount();

        return count > 0;
    }

    // 진행중인 미션 진행 완료로 바꾸기
    @Override
    public void updateStatusToComplete(Long memberMissionId) {
        jpaQueryFactory
                .update(memberMission)
                .where(memberMission.id.eq(memberMissionId))
                .set(memberMission.status, MissionStatus.COMPLETE)
                .execute();

        entityManager.flush();
        entityManager.clear();
    }
}