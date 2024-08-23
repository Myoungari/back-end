package myongari.backend.club.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ClubFakeRepository implements ClubRepository {

    private static final List<Club> clubs = Collections.synchronizedList(new ArrayList<>());
    private static AtomicLong id = new AtomicLong(1);

    @Override
    public Page<ClubSimple> findClubSimpleAll(Pageable pageable) {
        List<ClubSimple> clubSimples = clubs.stream()
                .map(club -> new ClubSimple(
                        club.getName(),
                        club.getImage(),
                        club.getApply().getRecruitmentStatus(),
                        club.getIntroduce()))
                .toList();

        return new PageImpl<>(clubSimples, pageable, clubs.size());
    }

    @Override
    public List<ClubName> findClubNamesByCategoryName(String categoryName) {
        List<ClubName> clubNames = clubs.stream()
                .filter(club -> club.getCategory().getName().equals(categoryName))
                .map(club -> new ClubName(club.getApply().getRecruitmentStatus(), club.getName()))
                .toList();

        return clubNames;
    }

    @Override
    public Optional<Club> findClubById(Long id) {
        return clubs.stream()
                .filter(each -> each.getId() == id - 1)
                .findFirst();
    }

    @Override
    public Long save(Club club) {
        clubs.add(club);
        id.getAndIncrement();
        return id.get();
    }
}
