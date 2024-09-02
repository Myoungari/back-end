package myongari.backend.club.presentation;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.ClubService;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubNamesAndDetail;
import myongari.backend.club.presentation.dto.ClubSimplePage;
import myongari.backend.common.response.Success;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/clubs")
    public ResponseEntity<Success> findClubSimpleAll(@PageableDefault Pageable pageable) {
        ClubSimplePage clubSimpleAll = clubService.findClubSimpleAll(pageable);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubSimpleAll));
    }

    @GetMapping("/{category_name}")
    public ResponseEntity<Success> findClubNamesByCategoryName(@PathVariable(name = "category_name") String categoryName) {
        ClubNamesAndDetail clubNamesAndClubDetail = clubService.findClubNamesAndClubDetailByCategoryName(categoryName);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubNamesAndClubDetail));
    }

    @GetMapping("/{category_name}/{club_id}")
    public ResponseEntity<Success> findClubNamesByCategoryNameAndClubId(@PathVariable(name = "category_name") String categoryName,
            @PathVariable(name = "club_id") long id) {
        ClubNamesAndDetail clubNamesAndClubDetail = clubService.findClubNamesAndClubDetailByCategoryName(categoryName, id);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubNamesAndClubDetail));
    }

    @GetMapping("/clubs/{id}")
    public ResponseEntity<Success> findClubById(@PathVariable(name = "id") int id) {
        Club club = clubService.findClubById(id);
        return ResponseEntity.status(200)
                .body(Success.of(200, club));
    }
}
