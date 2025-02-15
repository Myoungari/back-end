package myongari.backend.club.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.Image;
import myongari.backend.club.dto.ClubName;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubRegisterRequest;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public List<ClubSummary> findClubSummaryAll() {
        return clubRepository.findClubSummaryAll();
    }

    public ClubNamesAndDetail findClubNamesAndDetailByCategoryName(
            final String categoryName,
            final Long clubId
    ) {
        List<ClubName> clubNames = clubRepository.findClubNameAll(categoryName);
        if (clubNames.isEmpty()) {
            return null;
        }

        Club club;
        club = clubRepository.findClubById(Objects.requireNonNullElseGet(clubId, () -> clubNames.get(0).getId()))
                .orElseThrow(() -> new NoSuchElementException("동아리를 찾을 수 없습니다."));

        return ClubNamesAndDetail.builder()
                .clubNames(clubNames)
                .club(club)
                .build();
    }

    public void saveClub(
            final ClubRegisterRequest clubRegisterRequest,
            final Image image
    ) {
        Club club = clubRegisterRequest.toEntity(image);
        clubRepository.save(club);
    }
}
