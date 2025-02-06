package myongari.backend.club.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.Image;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubRegisterRequest;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public List<ClubSummary> findClubSummaryAll() {
        return clubRepository.findClubSummaryAll();
    }

    public ClubNamesAndDetail findClubNamesAndDetailByCategoryName(
            final String categoryName,
            final Long clubId
    ) {
        return clubRepository.findClubNamesAndDetailByCategoryName(categoryName, clubId);
    }

    public void saveClub(
            final ClubRegisterRequest clubRegisterRequest,
            final Image image
    ) {
        Club club = clubRegisterRequest.toEntity(image);
        clubRepository.save(club);
    }
}
