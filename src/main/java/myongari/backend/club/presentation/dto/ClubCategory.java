package myongari.backend.club.presentation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import myongari.backend.club.domain.Club;

@Getter
@AllArgsConstructor
public class ClubCategory {

    private List<ClubName> clubNames;
    private Club club;
}
