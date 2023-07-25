package ru.practicum.explore.service.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.repository.CategoryRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.exception.CustomConflictException;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AdminCategoryService {

    private EntityFinder entityFinder;
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(long catId) {
        entityFinder.gerCategoryById(catId);
        List<Event> events = entityFinder.getEventsByCatId(catId);
        if (events.size() > 0) {
            throw new CustomConflictException("The category is not empty");
        }
        categoryRepository.deleteById(catId);
    }

    public Category updateCategory(Category category) {
        Category cat = entityFinder.gerCategoryById(category.getId());
        cat.setName(category.getName());
        return categoryRepository.save(cat);
    }

}
