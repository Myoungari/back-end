package myongari.backend.club.presentation;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.ClubService;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimplePage;
import myongari.backend.common.response.Success;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<Success> findAllSimpleInfo(@RequestParam int page, @RequestParam int size) {
        ClubSimplePage clubSimpleAll = clubService.getClubSimpleAll(page, size);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubSimpleAll));
    }

    @GetMapping("/{category_name}")
    public ResponseEntity<Success> getClubNamesByCategory(@PathVariable(name = "category_name") String categoryName) {
        List<ClubName> clubNamesByCategory = clubService.getClubNamesByCategory(categoryName);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubNamesByCategory));
    }
}
