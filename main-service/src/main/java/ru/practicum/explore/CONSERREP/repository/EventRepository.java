package ru.practicum.explore.CONSERREP.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByUserId(long userId, Pageable pageable);

    Optional<Event> findByIdAndUserId(long eventId, long userId);

    Optional<Event> findById(long eventId);

    @Query("SELECT e FROM Event e " +
            "WHERE (e.user.id IN (:users) OR :users IS NULL) " +
            "AND (e.state IN (:states) OR :states IS NULL) " +
            "AND (e.category.id IN (:categories) OR :categories IS NULL) " +
            "AND (e.eventDate > :rangeStart OR :rangeStart IS NULL) " +
            "AND (e.eventDate < :rangeEnd OR :rangeEnd IS NULL) ")
    List<Event> findAllForAdmin(
            @Param("users") List<Long> users,
            @Param("states") List<State> states,
            @Param("categories") List<Long> categories,
            @Param("rangeStart") LocalDateTime rangeStart,
            @Param("rangeEnd") LocalDateTime rangeEnd,
            Pageable pageable);


    @Query("SELECT e FROM Event e " +
            "WHERE ((lower(e.annotation) LIKE concat('%',lower(:text),'%') " +
            "OR lower(e.description) LIKE concat('%',lower(:text),'%')) " +
            "OR :text IS NULL ) " +
            "AND (e.category.id IN (:categories) OR :categories IS NULL) " +
            "AND (e.paid IN (:paid) OR :paid IS NULL) " +
            "AND (e.eventDate > :rangeStart OR :rangeStart IS NULL) " +
            "AND (e.eventDate < :rangeEnd OR :rangeEnd IS NULL) " +
            "AND (e.participantLimit != 0 AND e.confirmedRequests < e.participantLimit)")
    List<Event> findAllForAllWithOnlyAvailable(@Param("text") String text,
                                               @Param("categories") List<Long> categories,
                                               @Param("paid") Boolean paid,
                                               @Param("rangeStart") LocalDateTime rangeStart,
                                               @Param("rangeEnd") LocalDateTime rangeEnd,
                                               Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE ((lower(e.annotation) LIKE concat('%',lower(:text),'%') " +
            "OR lower(e.description) LIKE concat('%',lower(:text),'%')) " +
            "OR :text IS NULL ) " +
            "AND (e.category.id IN (:categories) OR :categories IS NULL) " +
            "AND (e.paid IN (:paid) OR :paid IS NULL) " +
            "AND (e.eventDate > :rangeStart OR :rangeStart IS NULL) " +
            "AND (e.eventDate < :rangeEnd OR :rangeEnd IS NULL) ")
    List<Event> findAllForAllWithoutOnlyAvailable(@Param("text") String text,
                                                  @Param("categories") List<Long> categories,
                                                  @Param("paid") Boolean paid,
                                                  @Param("rangeStart") LocalDateTime rangeStart,
                                                  @Param("rangeEnd") LocalDateTime rangeEnd,
                                                  Pageable pageable);


    Set<Event> findAllByIdIn(Set<Long> eventIds);

    List<Event> findAllByCategoryId(long catId);
}
