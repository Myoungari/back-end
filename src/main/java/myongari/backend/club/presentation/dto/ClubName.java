package myongari.backend.club.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myongari.backend.club.domain.State;

@Getter
@AllArgsConstructor
public class ClubName {

    private State recruitmentStatus;
    private String clubName;

}
