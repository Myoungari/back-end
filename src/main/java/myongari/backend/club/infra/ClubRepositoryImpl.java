package myongari.backend.club.infra;

import java.util.List;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.entity.Club;
import myongari.backend.club.presentation.dto.ClubCount;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {

    private final ClubJpaRepository clubJpaRepository;

    @Override
    public List<ClubSimple> getClubSimpleAll() {
        return clubJpaRepository.getClubSimpleAll();
    }

    @Override
    public List<ClubName> getClubNamesByCategory(String categoryName) {
        return clubJpaRepository.getClubNames(categoryName);
    }

    @Override
    public ClubCount getClubCount() {
        int count = clubJpaRepository.countAllBy();
        return new ClubCount(count);
    }

    @Override
    public Club getClubById(Long id) {
        return clubJpaRepository.getClubById(id);
    }
}
