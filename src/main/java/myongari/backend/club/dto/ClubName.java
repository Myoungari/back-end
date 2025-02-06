package myongari.backend.club.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myongari.backend.club.domain.State;

@Getter
@AllArgsConstructor
public class ClubName {

    private long id;
    private String clubName;
    private State recruitmentStatus;
}
