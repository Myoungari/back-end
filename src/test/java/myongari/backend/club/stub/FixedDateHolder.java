package myongari.backend.club.stub;

import java.time.LocalDate;
import myongari.backend.club.application.port.DateHolder;

public class FixedDateHolder implements DateHolder {

    private final LocalDate date;

    public FixedDateHolder(int month, int day) {
        this.date = LocalDate.of(2024, month, day);
    }

    @Override
    public LocalDate getDate() {
        return date;
    }
}
