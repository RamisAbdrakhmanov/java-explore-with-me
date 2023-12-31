package ru.practicum.explore.service.all;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.repository.CategoryRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.category.Category;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AllCategoriesService {

    private CategoryRepository categoryRepository;
    private EntityFinder entityFinder;

    public List<Category> getCategories(int from, int size) {
        Pageable pageable = MyPageRequest.of(from, size);
        return categoryRepository.findAllBy(pageable);
    }

    public Category getCategory(long catId) {
        return entityFinder.gerCategoryById(catId);
    }
}
