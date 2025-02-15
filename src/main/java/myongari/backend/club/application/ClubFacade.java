package myongari.backend.club.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.domain.Image;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubRegisterRequest;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubFacade {

    private final ClubService clubService;
    private final ClubImageService clubImageService;

    public List<ClubSummary> findClubSummaryAll() {
        List<ClubSummary> clubSummaryAll = clubService.findClubSummaryAll();

        for (ClubSummary clubSummary : clubSummaryAll) {
            Image image = clubSummary.getImage();
            if (image == null) {
                continue;
            }
            Image downloaded = clubImageService.getImage(image);
            clubSummary.setImage(downloaded);
        }

        return clubSummaryAll;
    }

    public ClubNamesAndDetail findClubNamesAndClubDetailByCategoryName(
            String categoryName,
            Long clubId
    ) {
        ClubNamesAndDetail clubNamesAndDetail = clubService.findClubNamesAndDetailByCategoryName(categoryName, clubId);
        if (clubNamesAndDetail == null) {
            return null;
        }
        Image image = clubNamesAndDetail.getClub().getImage();
        if (image != null) {
            Image downloaded = clubImageService.getImage(image);
            clubNamesAndDetail.getClub().setImage(downloaded);
        }

        return clubNamesAndDetail;
    }

    @Transactional
    public void saveClub(
            final ClubRegisterRequest clubRegisterRequest,
            final MultipartFile image
    ) {
        // save image to aws s3
        Image savedImage = clubImageService.saveImage(image);
        log.info("image saved successfully");

        // save club to db
        clubService.saveClub(clubRegisterRequest, savedImage);
        log.info("club saved successfully");
    }
}
