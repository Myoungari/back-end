package myongari.backend.club.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myongari.backend.club.domain.State;

@Getter
@AllArgsConstructor
public class ClubSimple {

    private String name;
    private byte[] image;
    private State recruitmentStatus;
    private String introduce;

    public ClubSimple(String name, State recruitmentStatus, String introduce) {
        this.name = name;
        this.recruitmentStatus = recruitmentStatus;
        this.introduce = introduce;
    }
}