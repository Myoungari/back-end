package myongari.backend.club.infra;

import static myongari.backend.club.domain.QCategory.category;
import static myongari.backend.club.domain.QClub.club;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.dto.ClubName;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<ClubSummary> findClubSummaryAll() {
        return queryFactory.select(Projections.constructor(ClubSummary.class,
                        club.id,
                        club.name,
                        club.image,
                        club.apply.recruitmentStatus,
                        club.introduce,
                        category.name
                ))
                .from(club)
                .join(category).on(club.categoryId.eq(category.id))
                .fetch();
    }

    public List<ClubName> findClubNameAll(String categoryName) {
        return queryFactory.select(Projections.constructor(ClubName.class,
                        club.id,
                        club.name,
                        club.apply.recruitmentStatus
                ))
                .from(club)
                .join(category).on(club.categoryId.eq(category.id))
                .where(category.name.eq(categoryName))
                .orderBy(club.apply.recruitmentStatus.asc(), club.name.asc())
                .fetch();
    }
}
