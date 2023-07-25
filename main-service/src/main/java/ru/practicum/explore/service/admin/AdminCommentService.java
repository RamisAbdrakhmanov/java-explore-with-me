package ru.practicum.explore.service.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.repository.CommentRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.model.comment.Comment;
import ru.practicum.explore.model.comment.dto.NewCommentDto;

@Service
@AllArgsConstructor
public class AdminCommentService {

    private EntityFinder entityFinder;
    private CommentRepository commentRepository;

    public Comment updateComment(long commentId, NewCommentDto newCommentDto) {
        Comment comment = entityFinder.getCommentById(commentId);
        comment.setText(newCommentDto.getText());
        return commentRepository.save(comment);
    }
}
