package myongari.backend.club.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.dto.ClubCount;
import myongari.backend.club.dto.ClubDetail;
import myongari.backend.club.dto.ClubName;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubRegisterRequest;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubCount getClubCount() {
        return ClubCount.builder()
                .count(clubRepository.findClubCount())
                .build();
    }

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

        ClubDetail clubDetail = clubRepository.findClubDetailById(
                        Objects.requireNonNullElseGet(clubId, () -> clubNames.get(0).getId()))
                .orElseThrow(() -> new NoSuchElementException("동아리를 찾을 수 없습니다."));

        return ClubNamesAndDetail.builder()
                .clubNames(clubNames)
                .clubDetail(clubDetail)
                .build();
    }

    public Club saveClub(
            final ClubRegisterRequest clubRegisterRequest
    ) {
        Club club = clubRegisterRequest.toEntity();
        return clubRepository.save(club);
    }
}
