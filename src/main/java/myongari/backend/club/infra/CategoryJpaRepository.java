package myongari.backend.club.infra;

import myongari.backend.club.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
