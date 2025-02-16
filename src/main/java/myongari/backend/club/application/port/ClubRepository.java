package myongari.backend.club.application.port;

import java.util.List;
import java.util.Optional;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.ImageType;
import myongari.backend.club.dto.ClubDetail;
import myongari.backend.club.dto.ClubName;
import myongari.backend.club.dto.ClubSummary;

public interface ClubRepository {

    List<ClubSummary> findClubSummaryAll();
    List<ClubName> findClubNameAll(String categoryName);
    Optional<ClubDetail> findClubDetailById(Long clubId);
    List<Image> findImage(Long clubId, ImageType imageType);
    List<Club> findClubsAll();

    void saveAll(List<Club> clubs);
    Club save(Club club);
    void saveImage(Image image);
}
