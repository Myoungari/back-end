package myongari.backend.club.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.ClubService;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import myongari.backend.common.response.Success;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<Success> findAllSimpleInfo() {
        List<ClubSimple> allClubSimple = clubService.getClubSimpleAll();
        return ResponseEntity.status(200)
                .body(Success.of(200, allClubSimple));
    }

    @GetMapping("/{category_name}")
    public ResponseEntity<Success> getClubNamesByCategory(@PathVariable(name = "category_name") String categoryName) {
        List<ClubName> clubNamesByCategory = clubService.getClubNamesByCategory(categoryName);
        return ResponseEntity.status(200)
                .body(Success.of(200, clubNamesByCategory));
    }
}
