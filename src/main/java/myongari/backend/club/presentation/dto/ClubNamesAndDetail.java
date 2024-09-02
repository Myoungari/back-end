package myongari.backend.club.presentation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import myongari.backend.club.domain.Club;

@Getter
@AllArgsConstructor
@Builder
public class ClubNamesAndDetail {

    private List<ClubName> clubNames;
    private Club club;
}
