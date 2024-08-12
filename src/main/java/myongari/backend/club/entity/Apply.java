package myongari.backend.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Apply {

    @Column(nullable = false, name = "recruitment_status")
    @Enumerated(EnumType.STRING)
    private State recruitmentStatus;
    @Column(name = "apply_link")
    private String applyLink;

    private String qualifications;
}
