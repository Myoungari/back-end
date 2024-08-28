package myongari.backend.club.infra;

import java.time.LocalDate;
import myongari.backend.club.application.port.DateHolder;

public class SystemDateHolder implements DateHolder {

    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }
}
