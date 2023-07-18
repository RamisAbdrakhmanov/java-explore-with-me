package ru.practicum.explore.CONSERREP.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Pageable> {

    List<User> findAllByIdIn(List<Long> id, Pageable pageable);

    void deleteById(long id);

    Optional<User> findById(long id);
}
