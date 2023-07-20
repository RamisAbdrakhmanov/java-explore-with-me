package ru.practicum.explore.CONSERREP.service.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.CategoryRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.model.category.Category;

import javax.transaction.Transactional;

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
        categoryRepository.deleteById(catId);
    }

    public Category updateCategory(Category category) {
        Category cat = entityFinder.gerCategoryById(category.getId());
        cat.setName(category.getName());
        return categoryRepository.save(cat);
    }

}
