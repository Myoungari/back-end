package myongari.backend.club.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryFakeRepository implements CategoryRepository {

    private static final Logger log = LoggerFactory.getLogger(CategoryFakeRepository.class);
    private final List<Category> categories = Collections.synchronizedList(new ArrayList<>());
    private AtomicInteger id = new AtomicInteger(1);

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
