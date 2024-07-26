package myongari.backend.president.infra;

import myongari.backend.president.entity.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentJpaRepository extends JpaRepository<President, Long> {

}
