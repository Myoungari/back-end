package myongari.backend.club.infra;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.ImageType;
import myongari.backend.club.dto.ClubDetail;
import myongari.backend.club.dto.ClubName;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {

    private final ClubJpaRepository clubJpaRepository;
    private final ClubDslRepository clubDslRepository;
    private final ClubImageJpaRepository clubImageJpaRepository;

    @Override
    public Long findClubCount() {
        return clubJpaRepository.count();
    }

    @Override
    public List<ClubSummary> findClubSummaryAll() {
        return clubDslRepository.findClubSummaryAll();
    }

    @Override
    public List<ClubName> findClubNameAll(final String categoryName) {
        return clubDslRepository.findClubNameAll(categoryName);
    }

    @Override
    public Optional<ClubDetail> findClubDetailById(final Long clubId) {
        return clubDslRepository.findClubDetailById(clubId);
    }

    @Override
    public List<Club> findClubsAll() {
        return clubJpaRepository.findAll();
    }

    @Override
    public List<Image> findImage(
            final Long clubId,
            final ImageType imageType
    ) {
        return clubImageJpaRepository.findByClubIdAndImageType(clubId, imageType);
    }

    @Override
    public void saveAll(final List<Club> clubs) {
        clubJpaRepository.saveAll(clubs);
    }

    @Override
    public Club save(final Club club) {
        return clubJpaRepository.save(club);
    }

    @Override
    public void saveImage(final Image image) {
        clubImageJpaRepository.save(image);
    }
}
