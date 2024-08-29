package myongari.backend.club.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    Pending("Recruiting"),
    Recruiting("Recruiting"),
    Recruited("Recruited"),
    ClosedEarly("Recruited"),
    Cancelled("Recruited"),
    Unplanned("Recruited");

    @JsonValue
    private final String msg;
}
