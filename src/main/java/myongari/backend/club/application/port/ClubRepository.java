package myongari.backend.club.application.port;

import java.util.List;

import myongari.backend.club.entity.Club;
import myongari.backend.club.presentation.dto.ClubCount;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;

public interface ClubRepository {

    List<ClubSimple> getClubSimpleAll();

    List<ClubName> getClubNamesByCategory(String categoryName);

    ClubCount getClubCount();

    Club getClubById(Long id);
}
