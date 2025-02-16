package myongari.backend.club.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ClubNamesAndDetail {

    private List<ClubName> clubNames;
    private ClubDetail clubDetail;
}
