package myongari.backend.club.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.category.entity.Category;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.presentation.dto.ClubCount;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public List<ClubSimple> getClubSimpleAll() {
        return clubRepository.getClubSimpleAll();
    }

    public List<ClubName> getClubNamesByCategory(String categoryName) {
        return clubRepository.getClubNamesByCategory(categoryName);
    }

    public ClubCount getClubCount() {
        return clubRepository.getClubCount();
    }
}
