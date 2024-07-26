package myongari.backend.club.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myongari.backend.club.entity.State;

@Getter
@AllArgsConstructor
public class ClubSimple {

    private String name;
    private String imageLink;
    private State recruitmentStatus;
    private String introduce;

}
