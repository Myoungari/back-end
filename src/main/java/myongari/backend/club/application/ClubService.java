package myongari.backend.club.application;

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
import myongari.backend.club.presentation.dto.ClubNamesAndDetail;
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
            Image image = clubSimple.getImage();
            if (image == null) {
                continue;
            }
            Image downloaded = clubImageStorage.downloadImage(image.getUuid());
            clubSimple.setImage(downloaded);
        }

        return ClubSimplePage.from(clubSimpleAll);
    }

    @Transactional(readOnly = true)
    public ClubNamesAndDetail findClubNamesAndClubDetailByCategoryName(String categoryName) {
        categoryRepository.findCategoryByName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("카테고리를 찾지 못했습니다."));

        List<ClubName> clubNames = clubRepository.findClubNamesByCategoryName(categoryName);
        ClubName clubNameFirst = clubNames.stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("카테고리 내 동아리가 존재하지 않습니다."));

        Club clubDetail = clubRepository.findClubById(clubNameFirst.getId())
                .orElseThrow((() -> new NoSuchElementException("동아리를 찾지 못했습니다.")));

        Image image = clubDetail.getImage();
        if (image != null) {
            Image downloaded = clubImageStorage.downloadImage(image.getUuid());
            clubDetail.setImage(downloaded);
        }

        return ClubNamesAndDetail.builder()
                .clubNames(clubNames)
                .club(clubDetail)
                .build();
    }

    @Transactional(readOnly = true)
    public ClubNamesAndDetail findClubNamesAndClubDetailByCategoryName(String categoryName, long id) {
        categoryRepository.findCategoryByName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("카테고리를 찾지 못했습니다."));

        List<ClubName> clubNames = clubRepository.findClubNamesByCategoryName(categoryName);
        if (clubNames.isEmpty()) {
            throw new NoSuchElementException("카테고리 내 동아리가 존재하지 않습니다.");
        }

        Club clubAsCategory = clubNames.stream()
                .map(ClubName::getId)
                .filter(clubId -> clubId == id)
                .map(clubId -> clubRepository.findClubById(clubId)
                        .orElseThrow(() -> new NoSuchElementException("동아리를 찾지 못했습니다.")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("동아리가 카테고리 내에 속해있지 않습니다."));

        Image image = clubAsCategory.getImage();
        if (image != null) {
            Image downloaded = clubImageStorage.downloadImage(image.getUuid());
            clubAsCategory.setImage(downloaded);
        }

        return ClubNamesAndDetail.builder()
                .clubNames(clubNames)
                .club(clubAsCategory)
                .build();
    }

    @Transactional(readOnly = true)
    public Club findClubById(long id) {
        Club club = clubRepository.findClubById(id)
                .orElseThrow(() -> new NoSuchElementException("동아리를 찾지 못했습니다."));

        Image image = club.getImage();
        if (image != null) {
            Image downloaded = clubImageStorage.downloadImage(image.getUuid());
            club.setImage(downloaded);
        }

        return club;
    }
}
