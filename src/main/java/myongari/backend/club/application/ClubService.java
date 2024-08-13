package myongari.backend.club.application;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import myongari.backend.club.presentation.dto.ClubSimplePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ClubSimplePage getClubSimpleAll(Pageable pageable) {
        Page<ClubSimple> clubSimpleAll = clubRepository.getClubSimpleAll(pageable);

        return ClubSimplePage.from(clubSimpleAll);
    }

    @Transactional(readOnly = true)
    public List<ClubName> getClubNamesByCategory(String categoryName) {
        categoryRepository.getCategoryByName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("카테고리를 찾지 못했습니다."));

        return clubRepository.getClubNamesByCategory(categoryName);
    }

    @Transactional(readOnly = true)
    public Club getClubById(long id) {
        return clubRepository.getClubById(id)
                .orElseThrow(() -> new NoSuchElementException("동아리를 찾지 못했습니다."));
    }
}
