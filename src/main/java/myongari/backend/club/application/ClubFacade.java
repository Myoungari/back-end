package myongari.backend.club.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.domain.Image;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubSummary;
import myongari.backend.club.dto.ClubSummaryPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubFacade {

    private final ClubService clubService;
    private final ClubImageService clubImageService;

    public ClubSummaryPage findClubSimpleAll(Pageable pageable) {
        Page<ClubSummary> clubSimpleAll = clubService.findClubSimpleAll(pageable);

        for (ClubSummary clubSummary : clubSimpleAll) {
            Image image = clubSummary.getImage();
            if (image == null) {
                continue;
            }
            Image downloaded = clubImageService.getImage(image.getUuid());
            clubSummary.setImage(downloaded);
        }

        return ClubSummaryPage.from(clubSimpleAll);
    }

    public ClubNamesAndDetail findClubNamesAndClubDetailByCategoryName(
            String categoryName,
            Long clubId
    ) {
        ClubNamesAndDetail clubNamesAndDetail = clubService.findClubNamesAndDetailByCategoryName(categoryName, clubId);

        Image image = clubNamesAndDetail.getClub().getImage();
        if (image != null) {
            Image downloaded = clubImageService.getImage(image.getUuid());
            clubNamesAndDetail.getClub().setImage(downloaded);
        }

        return clubNamesAndDetail;
    }
}
