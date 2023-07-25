package ru.practicum.explore.service.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.comment.Comment;
import ru.practicum.explore.model.comment.dto.NewCommentDto;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.exception.CustomNotFoundException;
import ru.practicum.explore.model.exception.CustomValidException;
import ru.practicum.explore.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserCommentService {

    private CommentRepository commentRepository;
    private EntityFinder entityFinder;

    public List<Comment> getComments(long userId, LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size) {
        Pageable pageable = MyPageRequest.of(from, size);
        return commentRepository.findAllCommentForAuthor(userId, rangeStart, rangeEnd, pageable);
    }

    public Comment getComment(long userId, long commentId) {
        return entityFinder.getCommentByIdAndUserId(userId, commentId);
    }

    public Comment addComment(Comment comment) {
        if (comment.getEvent().getState() == State.PENDING) {
            throw new CustomValidException("Event must be PUBLISHED");
        }
        return commentRepository.save(comment);
    }

    public Comment updateComment(long userId, long commentId, NewCommentDto newCommentDto) {

        Comment comment = commentRepository.findByIdAndAuthorIdAndEventId(userId, commentId, newCommentDto.getEventId())
                .orElseThrow(() -> new CustomNotFoundException("There is no comment on the given parameters."));

        comment.setText(newCommentDto.getText());
        return commentRepository.save(comment);
    }

    public void deleteComment(long userId, long commentId) {
        Comment comment = entityFinder.getCommentByIdAndUserId(commentId, userId);
        commentRepository.delete(comment);
    }
}
