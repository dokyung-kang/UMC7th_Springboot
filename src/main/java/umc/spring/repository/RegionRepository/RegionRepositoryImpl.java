package umc.spring.repository.RegionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;


}