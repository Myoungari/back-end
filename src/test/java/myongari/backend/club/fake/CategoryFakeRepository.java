package myongari.backend.club.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.domain.Category;

public class CategoryFakeRepository implements CategoryRepository {

    private static final List<Category> categories = Collections.synchronizedList(new ArrayList<>());
    private static AtomicInteger id = new AtomicInteger(1);

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categories.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst();
    }

    @Override
    public long save(Category category) {
        categories.add(id.getAndIncrement() - 1, category);
        return id.get();
    }
}
