package myongari.backend.club.infra;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {

    private final ClubJpaRepository clubJpaRepository;

    @Override
    public Page<ClubSimple> findClubSimpleAll(Pageable pageable) {
        return clubJpaRepository.findClubSimpleAll(pageable);
    }

    @Override
    public List<ClubName> findClubNamesByCategoryName(String categoryName) {
        return clubJpaRepository.findClubNamesByCategoryName(categoryName);
    }

    @Override
    public Optional<Club> findClubById(Long id) {
        return clubJpaRepository.findById(id);
    }

    @Override
    public List<Club> findClubsCanUpdateStatus() {
        return clubJpaRepository.findClubsCanUpdateStatus();
    }

    @Override
    public Long save(Club club) {
        Club savedClub = clubJpaRepository.save(club);
        return savedClub.getId();
    }

    @Override
    public void saveAll(List<Club> clubs) {
        clubJpaRepository.saveAll(clubs);
    }
}
