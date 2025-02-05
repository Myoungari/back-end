package myongari.backend.club.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.dto.ClubNamesAndDetail;
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
}
