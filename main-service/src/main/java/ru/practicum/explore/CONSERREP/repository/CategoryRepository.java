package ru.practicum.explore.CONSERREP.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.category.Category;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Pageable> {

    Optional<Category> findById(long catId);

    void deleteById(long catId);

    List<Category> findAllBy(Pageable pageable);
}
