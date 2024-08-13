package myongari.backend.club.infra;

import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.domain.Category;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryJpaRepository.findByName(name);
    }

    @Override
    public long save(Category category) {
        Category savedCategory = categoryJpaRepository.save(category);
        return savedCategory.getId();
    }
}
