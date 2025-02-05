package myongari.backend.club.application;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.presentation.dto.ClubNamesAndDetail;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public Page<ClubSimple> findClubSimpleAll(Pageable pageable) {
        return clubRepository.findClubSimpleAll(pageable);
    }

    public ClubNamesAndDetail findClubNamesAndDetailByCategoryName(
            final String categoryName,
            final Long clubId
    ) {
        return clubRepository.findClubNamesAndDetailByCategoryName(categoryName, clubId);
    }
}
