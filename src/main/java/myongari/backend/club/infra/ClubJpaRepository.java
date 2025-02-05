package myongari.backend.club.infra;

import myongari.backend.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubJpaRepository extends JpaRepository<Club, Long> {
}
