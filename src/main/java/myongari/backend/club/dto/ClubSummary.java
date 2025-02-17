package myongari.backend.club.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import myongari.backend.club.domain.State;

@Getter
@RequiredArgsConstructor
public class ClubSummary {

    private final long id;
    private final String name;
    @Setter
    private String thumbnailUrl;
    private final State recruitmentStatus;
    private final String introduce;
    private final String categoryName;
}