package myongari.backend.club.presentation;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.ClubService;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimplePage;
import myongari.backend.common.response.Success;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/clubs")
    public ResponseEntity<Success> findAllSimpleInfo(@PageableDefault Pageable pageable) {
        ClubSimplePage clubSimpleAll = clubService.getClubSimpleAll(pageable);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubSimpleAll));
    }

    @GetMapping("/categories/{category_name}/clubs")
    public ResponseEntity<Success> getClubNamesByCategory(@PathVariable(name = "category_name") String categoryName) {
        List<ClubName> clubNamesByCategory = clubService.getClubNamesByCategory(categoryName);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubNamesByCategory));
    }

    @GetMapping("/clubs/{id}")
    public ResponseEntity<Success> getClubById(@PathVariable(name = "id") int id) {
        Club club = clubService.getClubById(id);
        return ResponseEntity.status(200)
                .body(Success.of(200, club));
    }
}
