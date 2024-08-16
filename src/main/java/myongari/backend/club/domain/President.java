package myongari.backend.club.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class President {

    @Column(nullable = false, name = "president_name")
    private String name;
    @Column(name = "president_contact")
    private String contact;
    @Column(name = "president_email")
    private String email;

}
