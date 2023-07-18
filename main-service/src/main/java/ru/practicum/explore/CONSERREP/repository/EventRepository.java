package ru.practicum.explore.CONSERREP.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.user.User;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Pageable> {

    List<Event> findAllByUserId(long userId, Pageable pageable);

    Optional<Event> findByIdAndUserId(long eventId, long userId);

    Optional<Event> findById(long eventId);

    @Query("SELECT Event FROM Event " +
            "WHERE eventDate BETWEEN ?4 AND  ?5 " +
            "AND (user IN ?1 OR ?1 is NULL) " +
            "AND (state IN ?2 OR ?2 is NULL) " +
            "AND (category.id IN ?3 OR ?3 is NULL) ")
    List<Event> findAllForAdmin(List<Integer> users, List<State> states, List<Integer> categories,
                                LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);
}
