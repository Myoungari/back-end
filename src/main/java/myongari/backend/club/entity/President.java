package myongari.backend.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class President {

    @Column(nullable = false)
    private String name;
    private String contact;
    private String email;

    public President(String name, String contact, String email) {
        this.name = name;
        this.contact = contact;
        this.email = email;
    }
}
