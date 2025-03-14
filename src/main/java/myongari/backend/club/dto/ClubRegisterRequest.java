package myongari.backend.club.dto;

import java.time.LocalDate;
import myongari.backend.club.domain.Apply;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.President;
import myongari.backend.club.domain.State;

public record ClubRegisterRequest(
        // basic
        String name,
        String location,
        String snsLink,
        String introduce,
        String activity,
        // apply
        State recruitmentStatus,
        String applyLink,
        String recruitmentMethod,
        LocalDate recruitStartDate,
        LocalDate recruitEndDate,
        String qualifications,
        // president
        String presidentName,
        String presidentContact,
        String presidentEmail,
        // category
        long categoryId
) {

    public Club toEntity() {
        return Club.builder()
                .name(name)
                .location(location)
                .snsLink(snsLink)
                .introduce(introduce)
                .activity(activity)
                .apply(Apply.builder().
                        recruitmentStatus(recruitmentStatus)
                        .applyLink(applyLink)
                        .recruitmentMethod(recruitmentMethod)
                        .recruitStartDate(recruitStartDate)
                        .recruitEndDate(recruitEndDate)
                        .qualifications(qualifications)
                        .build())
                .president(President.builder()
                        .name(presidentName)
                        .contact(presidentContact)
                        .email(presidentEmail)
                        .build())
                .categoryId(categoryId)
                .build();
    }
}
