package ru.practicum.explore.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.explore.model.comment.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Pageable> {

    Optional<Comment> findById(long id);


    Optional<Comment> findByIdAndAuthorId(long id, long authorId);

    @Query("SELECT c FROM Comment c " +
            "WHERE c.author.id=:user " +
            "AND c.created BETWEEN :rangeStart AND :rangeEnd " +
            "ORDER BY c.created DESC ")
    List<Comment> findAllCommentForAuthor(@Param("user") long userId,
                                          @Param("rangeStart") LocalDateTime rangeStart,
                                          @Param("rangeEnd") LocalDateTime rangeEnd,
                                          Pageable pageable);

    Optional<Comment> findByIdAndAuthorIdAndEventId(long id, long authorId, long eventId);

    List<Comment> findAllByEventIdOrderByCreatedDesc(long eventId, Pageable pageable);
}
