package ru.practicum.explore.CONSERREP.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.compilation.Compilation;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Pageable> {

    void deleteById(long compId);

    List<Compilation> findAllBy(Pageable pageable);

    List<Compilation> findAllByPinned(boolean pinned, Pageable pageable);

    Optional<Compilation> findById(long compId);
}
