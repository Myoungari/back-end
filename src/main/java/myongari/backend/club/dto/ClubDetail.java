package myongari.backend.club.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import myongari.backend.club.domain.State;

@Getter
@RequiredArgsConstructor
public class ClubDetail {
    // basic
    private final long id;
    private final String name;
    private final String location;
    private final String snsLink;
    private final String introduce;
    private final String activity;
    // apply
    private final State recruitmentStatus;
    private final String applyLink;
    private final String recruitmentMethod;
    private final LocalDate recruitStartDate;
    private final LocalDate recruitEndDate;
    private final String qualifications;
    // president
    private final String presidentName;
    private final String presidentContact;
    private final String presidentEmail;
    // category
    private final long categoryId;
    // thumbnail
    @Setter
    private String thumbnailUrl;
    // clubImages
    @Setter
    private List<String> clubImageUrls;
}
