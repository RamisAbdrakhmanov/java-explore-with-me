package ru.practicum.explore.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore.model.comment.Comment;
import ru.practicum.explore.model.comment.dto.CommentDto;
import ru.practicum.explore.model.comment.dto.CommentShortDto;
import ru.practicum.explore.model.comment.dto.NewCommentDto;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.user.User;

import java.time.LocalDateTime;

@UtilityClass
public class CommentMapper {

    public Comment toComment(User user, Event event, NewCommentDto newCommentDto) {
        return new Comment(0, newCommentDto.getText(), event, user, LocalDateTime.now());
    }

    public CommentShortDto toCommentShortDto(Comment comment) {
        return new CommentShortDto(comment.getId(), comment.getText(),
                UserMapper.toUserShortDto(comment.getAuthor()),
                comment.getCreated());
    }

    public CommentDto toCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(),
                EventMapper.toEventShortDto(comment.getEvent()),
                UserMapper.toUserShortDto(comment.getAuthor()),
                comment.getCreated());
    }
}
