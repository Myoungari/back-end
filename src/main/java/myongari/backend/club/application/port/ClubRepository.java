package myongari.backend.club.application.port;

import java.util.List;
import java.util.Optional;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubNamesAndDetail;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubRepository {

    Page<ClubSimple> findClubSimpleAll(Pageable pageable);
    ClubNamesAndDetail findClubNamesAndDetailByCategoryName(String categoryName, Long clubId);

    List<Club> findClubsAll();
    Optional<Club> findClubById(Long id);

    Long save(Club club);
    void saveAll(List<Club> clubs);
}
