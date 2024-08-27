package myongari.backend.club.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.domain.Category;

public class CategoryFakeRepository implements CategoryRepository {

    private final List<Category> categories = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong id = new AtomicLong(1);

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categories.stream()
                .filter(category -> category.getName().equals(name))
                .findAny();
    }

    @Override
    public long save(Category category) {
        if (category.getId() == 0L) {
            long savedId = id.getAndIncrement();
            categories.add((int) savedId - 1, category);
            return savedId;
        }
        categories.add((int) category.getId(), category);
        return category.getId();
    }
}
