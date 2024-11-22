package umc.spring.repository.MemberMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.QMemberMission;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

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
}