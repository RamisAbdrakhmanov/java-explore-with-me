package ru.practicum.explore.CONSERREP.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.admin.AdminCategoryService;
import ru.practicum.explore.mapper.CategoryMapper;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.model.category.dto.NewCategoryDto;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/admin/categories")
@AllArgsConstructor
public class AdminCategoryController {

    private AdminCategoryService adminCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("Start: ADMIN : \"addCategory\" : NewCategoryDto={}", newCategoryDto);
        return CategoryMapper.toCategoryDto(adminCategoryService.addCategory(CategoryMapper.toCategory(newCategoryDto)));
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long catId) {
        log.info("Start: ADMIN : \"deleteCategory\" : id={}", catId);
        adminCategoryService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategory(@PathVariable long catId, @Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("Start: ADMIN : \"updateCategory\" : id={}", catId);
        Category category = CategoryMapper.toCategory(catId, newCategoryDto);
        return CategoryMapper.toCategoryDto(adminCategoryService.updateCategory(category));
    }
}
