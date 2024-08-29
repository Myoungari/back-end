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

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubSimple(c.name, c.image, c.apply.recruitmentStatus, c.introduce) "
            + "FROM Club c")
    Page<ClubSimple> findClubSimpleAll(Pageable pageable);

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubName(cl.apply.recruitmentStatus ,cl.name) "
            + "FROM Club cl "
            + "JOIN cl.category c "
            + "WHERE c.name = :categoryName "
            + "ORDER BY CASE "
            + "WHEN cl.apply.recruitmentStatus = 'Recruiting' THEN 1 "
            + "WHEN cl.apply.recruitmentStatus = 'Recruited' THEN 2 "
            + "END, cl.name")
    List<ClubName> findClubNamesByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT c "
            + "FROM Club c "
            + "WHERE c.apply.recruitmentStatus = 'Recruiting' "
            + "OR c.apply.recruitmentStatus = 'Recruited' "
            + "OR c.apply.recruitmentStatus = 'Pending'")
    List<Club> findClubsCanUpdateStatus();
}
