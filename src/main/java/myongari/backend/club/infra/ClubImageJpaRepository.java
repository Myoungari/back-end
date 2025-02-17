package myongari.backend.club.infra;

import java.util.List;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubImageJpaRepository extends JpaRepository<Image, Long> {

    List<Image> findByClubIdAndImageType(Long clubId, ImageType imageType);
}
