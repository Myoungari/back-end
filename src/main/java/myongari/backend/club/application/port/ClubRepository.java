package myongari.backend.club.application.port;

import java.util.List;
import myongari.backend.club.domain.Club;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubSummary;

public interface ClubRepository {

    List<ClubSummary> findClubSummaryAll();
    ClubNamesAndDetail findClubNamesAndDetailByCategoryName(String categoryName, Long clubId);
    List<Club> findClubsAll();

    void saveAll(List<Club> clubs);
}
