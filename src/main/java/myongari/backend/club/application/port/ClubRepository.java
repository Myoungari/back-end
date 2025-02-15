package myongari.backend.club.application.port;

import java.util.List;
import java.util.Optional;
import myongari.backend.club.domain.Club;
import myongari.backend.club.dto.ClubName;
import myongari.backend.club.dto.ClubSummary;

public interface ClubRepository {

    List<ClubSummary> findClubSummaryAll();
    List<ClubName> findClubNameAll(String categoryName);
    Optional<Club> findClubById(Long clubId);
    List<Club> findClubsAll();

    void saveAll(List<Club> clubs);
    void save(Club club);
}
