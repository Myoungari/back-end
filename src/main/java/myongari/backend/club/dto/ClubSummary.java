package myongari.backend.club.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.State;

@Getter
@AllArgsConstructor
public class ClubSummary {

    private long id;
    private String name;
    private Image image;
    private State recruitmentStatus;
    private String introduce;
    private String categoryName;

    public void setImage(Image image) {
        this.image = image;
    }
}