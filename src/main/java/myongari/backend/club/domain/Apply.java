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

    public void setRecruitmentStatus(DateHolder dateHolder) {
        LocalDate now = dateHolder.getDate();
        if (recruitmentStatus == State.Recruiting || recruitmentStatus == State.Pending || recruitmentStatus == State.Recruited) {
            // 모집 중, 모집 예정, 모집 마감
            if (recruitStartDate != null && recruitEndDate != null) {
                recruitmentStatus = State.Recruited;
            }

            if (now.isAfter(recruitStartDate) && now.isBefore(recruitEndDate)) {
                recruitmentStatus = State.Recruiting;
            }

            if (now.isBefore(recruitStartDate)) {
                recruitmentStatus = State.Pending;
            }

            if (now.isAfter(recruitEndDate)) {
                recruitmentStatus = State.Recruited;
            }
        }
    }
}
