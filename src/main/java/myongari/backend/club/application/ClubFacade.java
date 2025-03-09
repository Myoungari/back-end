package myongari.backend.club.application;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.ImageType;
import myongari.backend.club.dto.ClubCount;
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

    public ClubCount getClubCount() {
        return clubService.getClubCount();
    }

    public List<ClubSummary> findClubSummaryAll() {
        List<ClubSummary> clubSummaryAll = clubService.findClubSummaryAll();
        if (clubSummaryAll == null) {
            return Collections.emptyList();
        }
        clubSummaryAll.forEach(clubImageService::setThumbnailPresignedUrl);
        return clubSummaryAll;
    }

    public ClubNamesAndDetail findClubNamesAndClubDetailByCategoryName(
            String categoryName,
            Long clubId
    ) {
        ClubNamesAndDetail clubNamesAndDetail = clubService.findClubNamesAndDetailByCategoryName(categoryName, clubId);
        if (clubNamesAndDetail.getClubNames() == null) {
            return clubNamesAndDetail;
        }
        // set thumbnail image link
        clubImageService.setThumbnailPresignedUrl(clubNamesAndDetail.getClubDetail());
        // set normal image links
        clubImageService.setImagePresignedUrls(clubNamesAndDetail.getClubDetail());
        return clubNamesAndDetail;
    }

    @Transactional
    public void saveClub(
            final ClubRegisterRequest clubRegisterRequest,
            final MultipartFile thumbnail,
            final List<MultipartFile> images
    ) {
        // save club
        Club club = clubService.saveClub(clubRegisterRequest);

        // save thumbnail image
        clubImageService.saveImage(club.getId(), ImageType.THUMBNAIL, thumbnail);
        log.info("thumbnail saved successfully");
        // save images
        if (images != null) {
            images.forEach(image -> clubImageService.saveImage(club.getId(), ImageType.NORMAL, image));
        }
    }
}
