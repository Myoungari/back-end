package myongari.backend.club.fake;

import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ClubFakeRepository implements ClubRepository {

    private static final List<Club> clubs = Collections.synchronizedList(new ArrayList<>());
    private static AtomicInteger id = new AtomicInteger(1);

    @Override
    public Page<ClubSimple> getClubSimpleAll(Pageable pageable) {
        List<ClubSimple> clubSimples = clubs.stream()
                .map(club -> new ClubSimple(
                        club.getName(),
                        club.getImageLink(),
                        club.getApply().getRecruitmentStatus(),
                        club.getIntroduce()))
                .toList();

        return new PageImpl<>(clubSimples, pageable, clubs.size());
    }

    @Override
    public List<ClubName> getClubNamesByCategory(String categoryName) {
        List<ClubName> clubNames = clubs.stream()
                .filter(club -> club.getCategory().getName().equals(categoryName))
                .map(club -> new ClubName(club.getName()))
                .toList();

        return clubNames;
    }

    @Override
    public Optional<Club> getClubById(Long id) {
        return clubs.stream()
                .filter(each -> each.getId() == id)
                .findFirst();
    }
}
