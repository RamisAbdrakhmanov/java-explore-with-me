package ru.practicum.explore.controller.all;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.service.all.AllCategoriesService;
import ru.practicum.explore.mapper.CategoryMapper;
import ru.practicum.explore.model.category.dto.CategoryDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class AllCategoriesController {

    private AllCategoriesService allCategoriesService;

    @GetMapping
    private List<CategoryDto> getCategories(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                            @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Start: ALL : \"getCategories\" : ");
        return allCategoriesService.getCategories(from, size)
                .stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());
    }

    @GetMapping("/{catId}")
    private CategoryDto getCategory(@PathVariable long catId) {
        log.info("Start: ALL : \"getCategory\" : catId={}", catId);
        return CategoryMapper.toCategoryDto(allCategoriesService.getCategory(catId));
    }
}
