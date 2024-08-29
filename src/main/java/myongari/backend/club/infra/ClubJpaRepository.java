package myongari.backend.club.infra;

import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubJpaRepository extends JpaRepository<Club, Long> {

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubSimple(c.id, c.name, c.image, c.apply.recruitmentStatus, c.introduce, c.category.name) "
            + "FROM Club c")
    Page<ClubSimple> findClubSimpleAll(Pageable pageable);

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubName(cl.id, cl.name, cl.apply.recruitmentStatus ) "
            + "FROM Club cl "
            + "JOIN cl.category c "
            + "WHERE c.name = :categoryName "
            + "ORDER BY CASE "
            + "WHEN cl.apply.recruitmentStatus IN (myongari.backend.club.domain.State.Pending, myongari.backend.club.domain.State.Recruiting) THEN 1 "
            + "WHEN cl.apply.recruitmentStatus IN (myongari.backend.club.domain.State.Unplanned, myongari.backend.club.domain.State.Recruited, myongari.backend.club.domain.State.ClosedEarly, myongari.backend.club.domain.State.Cancelled) THEN 2 "
            + "END, cl.name ASC")
    List<ClubName> findClubNamesByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT c "
            + "FROM Club c "
            + "WHERE c.apply.recruitmentStatus = 'Recruiting' "
            + "OR c.apply.recruitmentStatus = 'Recruited' "
            + "OR c.apply.recruitmentStatus = 'Pending'")
    List<Club> findClubsCanUpdateStatus();
}
