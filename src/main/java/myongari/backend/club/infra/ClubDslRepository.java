package myongari.backend.club.infra;

import static com.querydsl.core.types.Projections.list;
import static myongari.backend.club.domain.QCategory.category;
import static myongari.backend.club.domain.QClub.club;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.domain.Club;
import myongari.backend.club.dto.ClubName;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubDslRepository {

    private final JPAQueryFactory queryFactory;

    List<ClubSummary> findClubSummaryAll() {
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

    public ClubNamesAndDetail findClubNamesAndDetailByCategoryName(
            String categoryName,
            Long clubId
    ) {
        return queryFactory.select(Projections.constructor(ClubNamesAndDetail.class,
                        list(Projections.constructor(ClubName.class,
                                club.id,
                                club.name,
                                club.apply.recruitmentStatus
                        )),
                        Projections.constructor(Club.class, club)
                ))
                .from(club)
                .join(category).on(club.categoryId.eq(category.id))
                .where(
                        conditionCategoryNameEquals(categoryName),
                        conditionClubIdEquals(clubId)
                )
                .orderBy(club.apply.recruitmentStatus.asc(), club.name.asc())
                .fetchOne();
    }

    private BooleanExpression conditionCategoryNameEquals(String categoryName) {
        if (categoryName == null) {
            throw new IllegalArgumentException("카테고리를 찾지 못했습니다.");
        }
        return category.name.eq(categoryName);
    }

    private BooleanExpression conditionClubIdEquals(Long clubId) {
        return clubId != null ? club.id.eq(clubId) : null;
    }
}
