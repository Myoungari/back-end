package myongari.backend.club.infra;

import java.util.List;
import myongari.backend.club.entity.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubJpaRepository extends JpaRepository<Club, Long> {

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubSimple(c.name, c.imageLink, c.recruitmentStatus, c.introduce) " +
            "FROM Club c")
    List<ClubSimple> getClubSimpleAll();

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubName(cl.name) "
            + "FROM Club cl "
            + "JOIN cl.category c "
            + "WHERE c.name = :categoryName")
    List<ClubName> getClubNames(@Param("categoryName") String categoryName);
}
