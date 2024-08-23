package myongari.backend.club.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    Recruiting("모집중"),
    Recruited("모집완료");

    private String description;
}
