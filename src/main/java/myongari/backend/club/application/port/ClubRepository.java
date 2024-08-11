package myongari.backend.club.application.port;

import java.util.List;

import myongari.backend.club.entity.Club;
import myongari.backend.club.presentation.dto.ClubCount;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubRepository {

    Page<ClubSimple> getClubSimpleAll(Pageable pageable);

    List<ClubName> getClubNamesByCategory(String categoryName);

    ClubCount getClubCount();

    Club getClubById(Long id);
}
