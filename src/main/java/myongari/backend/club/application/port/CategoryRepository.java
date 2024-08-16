package myongari.backend.club.application.port;

import myongari.backend.club.domain.Category;

import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findCategoryByName(String name);

    long save(Category category);
}
