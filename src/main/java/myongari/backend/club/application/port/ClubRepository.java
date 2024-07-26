package myongari.backend.club.application.port;

import java.util.List;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;

public interface ClubRepository {

    List<ClubSimple> getClubSimpleAll();

    List<ClubName> getClubNamesByCategory(String categoryName);

}
