package myongari.backend.club.infra;

import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {

    private final ClubJpaRepository clubJpaRepository;
    private final ClubDslRepository clubDslRepository;

    @Override
    public List<ClubSummary> findClubSummaryAll() {
        return clubDslRepository.findClubSummaryAll();
    }

    @Override
    public ClubNamesAndDetail findClubNamesAndDetailByCategoryName(
            final String categoryName,
            final Long clubId
    ) {
        return clubDslRepository.findClubNamesAndDetailByCategoryName(categoryName, clubId);
    }

    @Override
    public List<Club> findClubsAll() {
        return clubJpaRepository.findAll();
    }

    @Override
    public void saveAll(final List<Club> clubs) {
        clubJpaRepository.saveAll(clubs);
    }

    @Override
    public void save(final Club club) {
        clubJpaRepository.save(club);
    }
}
