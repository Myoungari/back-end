package myongari.backend.club.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    Pending,
    Recruiting,
    Recruited,
    ClosedEarly,
    Cancelled,
    Unplanned;

    public String getRecruitState() {
        String state = "";
        if (this.equals(Recruiting) || this.equals(Pending)) {
            state = "Recruiting";
        }
        if (this.equals(Unplanned) || this.equals(Recruited) || this.equals(ClosedEarly) || this.equals(Cancelled)) {
            state = "Recruited";
        }
        return state;
    }
}
