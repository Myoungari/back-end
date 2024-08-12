package myongari.backend.club.infra;

import myongari.backend.club.entity.Club;
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

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubSimple(c.name, c.imageLink, c.apply.recruitmentStatus, c.introduce) " +
            "FROM Club c")
    Page<ClubSimple> getClubSimpleAll(Pageable pageable);

    @Query("SELECT new myongari.backend.club.presentation.dto.ClubName(cl.name) "
            + "FROM Club cl "
            + "JOIN cl.category c "
            + "WHERE c.name = :categoryName")
    List<ClubName> getClubNames(@Param("categoryName") String categoryName);

}
