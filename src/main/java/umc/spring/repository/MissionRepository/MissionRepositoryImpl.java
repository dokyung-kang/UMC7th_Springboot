package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMember;
import umc.spring.domain.QMission;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QStore store = QStore.store;
    private final QRegion region = QRegion.region;
    private final QMember member = QMember.member;

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    @Override
    public List<MissionResponseDTO.MissionStatusDTO> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status, Long currentMissionId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (memberId != null) {
            predicate.and(memberMission.member.id.eq(memberId));
        }

        if (status != null) {
            predicate.and(memberMission.status.eq(status));
        }

        if (currentMissionId != null) {
            predicate.and(memberMission.mission.id.lt(currentMissionId));
        }

        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionResponseDTO.MissionStatusDTO.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        memberMission.status
                ))
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.store, store)
                .where(predicate)
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public List<MissionResponseDTO.HomeMissionDTO> findMissionByMemberIdAndRegion(Long memberId, Long regionId, Long currentMissionId) {
        BooleanBuilder predicate = new BooleanBuilder();
//
//        if (memberId != null) {
//            predicate.and(member.id.eq(memberId));
//        }

        if (regionId != null) {
            predicate.and(region.id.eq(regionId));
        }

        predicate.and(mission.id.notIn(
                JPAExpressions
                        .select(memberMission.mission.id)
                        .from(memberMission)
                        .where(memberMission.member.id.eq(memberId))
        ));

        predicate.and(mission.deadline.gt(LocalDate.now()));

        if (currentMissionId != null) {
            predicate.and(mission.id.lt(currentMissionId));
        }
        NumberTemplate<Long> days_left = Expressions.numberTemplate(Long.class,
                "datediff({0}, {1})",
                mission.deadline,
                Expressions.constant(LocalDate.now()));

        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionResponseDTO.HomeMissionDTO.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        days_left
                ))
                .from(mission)
                .join(mission.store, store)
                .join(store.region, region)
                .where(predicate)
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }
}