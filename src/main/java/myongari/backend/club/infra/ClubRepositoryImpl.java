package myongari.backend.club.infra;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.entity.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {

    private final ClubJpaRepository clubJpaRepository;

    @Override
    public Page<ClubSimple> getClubSimpleAll(Pageable pageable) {
        return clubJpaRepository.getClubSimpleAll(pageable);
    }

    @Override
    public List<ClubName> getClubNamesByCategory(String categoryName) {
        return clubJpaRepository.getClubNames(categoryName);
    }

    @Override
    public Club getClubById(Long id) {
        return clubJpaRepository.findById(id).orElseThrow();
    }
}
