package myongari.backend.club.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.ClubFacade;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubRegisterRequest;
import myongari.backend.club.dto.ClubSummary;
import myongari.backend.common.response.Success;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClubController implements ClubSwagger {

    private final ClubFacade clubFacade;

    @GetMapping("/clubs")
    public ResponseEntity<Success<List<ClubSummary>>> getClubSummaryAll() {
        List<ClubSummary> clubSummaryAll = clubFacade.findClubSummaryAll();
        return ResponseEntity.status(200)
                .body(Success.of(200, clubSummaryAll));
    }

    @GetMapping("/{category_name}")
    public ResponseEntity<Success<ClubNamesAndDetail>> getClubNamesByCategoryName(
            @PathVariable(name = "category_name") String categoryName
    ) {
        ClubNamesAndDetail clubNamesAndClubDetail = clubFacade.findClubNamesAndClubDetailByCategoryName(categoryName, null);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubNamesAndClubDetail));
    }

    @GetMapping("/{category_name}/{club_id}")
    public ResponseEntity<Success<ClubNamesAndDetail>> getClubNamesByCategoryNameAndClubId(
            @PathVariable(name = "category_name") String categoryName,
            @PathVariable(name = "club_id") Long clubId
    ) {
        ClubNamesAndDetail clubNamesAndClubDetail = clubFacade.findClubNamesAndClubDetailByCategoryName(categoryName, clubId);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubNamesAndClubDetail));
    }

    @PostMapping(value = "/clubs", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveClub(
            @RequestPart(value = "clubRegisterRequest") ClubRegisterRequest clubRegisterRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        clubFacade.saveClub(clubRegisterRequest, image);
        return ResponseEntity.status(201).build();
    }
}
