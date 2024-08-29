package myongari.backend.club.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.application.port.DateHolder;
import myongari.backend.club.domain.Club;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruitStateScheduler {

    private final ClubRepository clubRepository;
    private final DateHolder dateHolder;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateRecruitState() {
        List<Club> clubs = clubRepository.findClubsCanUpdateStatus();

        clubs.forEach(club -> {
            log.debug("before: {} {}", club.getName(), club.getApply().getRecruitmentStatus());
            club.getApply().updateRecruitmentStatusFromRecruitDate(dateHolder);
            log.debug("after: {} {}", club.getName(), club.getApply().getRecruitmentStatus());
        });

        clubRepository.saveAll(clubs);
    }
}
