package myongari.backend.club.infra;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.dto.ClubName;
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
    public List<ClubName> findClubNameAll(final String categoryName) {
        return clubDslRepository.findClubNameAll(categoryName);
    }

    @Override
    public Optional<Club> findClubById(final Long clubId) {
        return clubJpaRepository.findById(clubId);
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
