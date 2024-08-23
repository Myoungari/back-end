package myongari.backend.club.application;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.Image;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import myongari.backend.club.presentation.dto.ClubSimplePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubService {

    private final ClubRepository clubRepository;
    private final CategoryRepository categoryRepository;
    private final ClubImageStorage clubImageStorage;

    @Transactional(readOnly = true)
    public ClubSimplePage findClubSimpleAll(Pageable pageable) {
        Page<ClubSimple> clubSimpleAll = clubRepository.findClubSimpleAll(pageable);

        for (ClubSimple clubSimple : clubSimpleAll) {
            try {
                byte[] imageAsByteData = clubImageStorage.downloadImage(clubSimple.getName(), clubSimple.getImage().getType());
                clubSimple.getImage().setImage(imageAsByteData);
            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return ClubSimplePage.from(clubSimpleAll);
    }

    @Transactional(readOnly = true)
    public List<ClubName> findClubNamesByCategoryName(String categoryName) {
        categoryRepository.findCategoryByName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("카테고리를 찾지 못했습니다."));

        return clubRepository.findClubNamesByCategoryName(categoryName);
    }

    @Transactional(readOnly = true)
    public Club findClubById(long id) {
        Club club = clubRepository.findClubById(id)
                .orElseThrow(() -> new NoSuchElementException("동아리를 찾지 못했습니다."));

        Image image = club.getImage();
        byte[] imageAsByteData = null;
        try {
            imageAsByteData = clubImageStorage.downloadImage(club.getName(), image.getType());
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        image.setImage(imageAsByteData);

        return club;
    }
}
