package myongari.backend.club.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myongari.backend.club.application.port.DateHolder;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Apply {

    @Column(nullable = false, name = "recruitment_status")
    @Enumerated(EnumType.STRING)
    private State recruitmentStatus;
    @Column(name = "apply_link")
    private String applyLink;
    @Column(name = "recruit_start_date")
    private LocalDate recruitStartDate;
    @Column(name = "recruit_end_date")
    private LocalDate recruitEndDate;
    private String qualifications;

    public void updateRecruitmentStatusFromRecruitDate(DateHolder dateHolder) {
        LocalDate now = dateHolder.getDate();

        if (!canUpdateRecruitmentStatus()) {
            return;
        }

        if (isRecruiting(now)) {
            recruitmentStatus = State.RECRUITING;
        }

        if (isRecruited(now)) {
            recruitmentStatus = State.RECRUITED;
        }
    }

    private boolean canUpdateRecruitmentStatus() {
        return recruitmentStatus == State.RECRUITING
                || recruitmentStatus == State.RECRUITED;
    }

    private boolean isRecruiting(LocalDate now) {
        return now.isEqual(recruitStartDate) ||
                (now.isAfter(recruitStartDate) && now.isBefore(recruitEndDate))
                || now.isEqual(recruitEndDate);
    }

    private boolean isRecruited(LocalDate now) {
        return now.isAfter(recruitEndDate);
    }
}
