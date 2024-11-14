package umc.spring.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMember;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    @Override
    public MemberResponseDTO.MemberInfoDTO findMemberByMemberId(Long memberId) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(member.id.eq(memberId));


        return jpaQueryFactory
                .select(Projections.constructor(
                        MemberResponseDTO.MemberInfoDTO.class,
                        member.id,
                        member.name,
                        member.email,
                        member.point,
                        member.phoneNum
                ))
                .from(member)
                .where(predicate)
                .fetchOne();
    }

}