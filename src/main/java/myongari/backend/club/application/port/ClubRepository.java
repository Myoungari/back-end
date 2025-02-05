package myongari.backend.club.application.port;

import java.util.List;
import myongari.backend.club.domain.Club;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubRepository {

    Page<ClubSimple> findClubSimpleAll(Pageable pageable);
    ClubNamesAndDetail findClubNamesAndDetailByCategoryName(String categoryName, Long clubId);
    List<Club> findClubsAll();

    void saveAll(List<Club> clubs);
}
