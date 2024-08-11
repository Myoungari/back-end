package myongari.backend.club.application;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.presentation.dto.ClubCount;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import myongari.backend.club.presentation.dto.ClubSimplePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubSimplePage getClubSimpleAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<ClubSimple> clubSimpleAll = clubRepository.getClubSimpleAll(pageRequest);

        return ClubSimplePage.from(clubSimpleAll);
    }

    public List<ClubName> getClubNamesByCategory(String categoryName) {
        return clubRepository.getClubNamesByCategory(categoryName);
    }

    public ClubCount getClubCount() {
        return clubRepository.getClubCount();
    }
}
