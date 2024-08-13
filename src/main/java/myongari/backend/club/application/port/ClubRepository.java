package myongari.backend.club.application.port;

import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClubRepository {

    Page<ClubSimple> getClubSimpleAll(Pageable pageable);

    List<ClubName> getClubNamesByCategory(String categoryName);

    Optional<Club> getClubById(Long id);
}
