package myongari.backend.club.infra;

import static myongari.backend.club.domain.QCategory.category;
import static myongari.backend.club.domain.QClub.club;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.dto.ClubDetail;
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
                .orderBy(club.apply.recruitmentStatus.desc(), club.name.asc())
                .fetch();
    }

    public Optional<ClubDetail> findClubDetailById(Long clubId) {
        return Optional.ofNullable(queryFactory.select(Projections.constructor(ClubDetail.class,
                        club.id,
                        club.name,
                        club.location,
                        club.snsLink,
                        club.introduce,
                        club.activity,
                        club.apply.recruitmentStatus,
                        club.apply.applyLink,
                        club.apply.recruitmentMethod,
                        club.apply.recruitStartDate,
                        club.apply.recruitEndDate,
                        club.apply.qualifications,
                        club.president.name,
                        club.president.contact,
                        club.president.email,
                        category.id
                ))
                .from(club)
                .join(category).on(club.categoryId.eq(category.id))
                .where(club.id.eq(clubId))
                .fetchOne());
    }
}
