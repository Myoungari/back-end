package myongari.backend.club.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    RECRUITING("Recruiting"),
    RECRUITED("Recruited");

    @JsonValue
    private final String msg;
}
