package ru.practicum.explore.CONSERREP.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.user.UserCommentService;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.mapper.CommentMapper;
import ru.practicum.explore.model.comment.Comment;
import ru.practicum.explore.model.comment.dto.CommentDto;
import ru.practicum.explore.model.comment.dto.CommentShortDto;
import ru.practicum.explore.model.comment.dto.NewCommentDto;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.user.User;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/users/{userId}/comments")
@AllArgsConstructor
public class UserCommentController {

    private UserCommentService userCommentService;
    private EntityFinder entityFinder;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getComments(@PathVariable long userId,
                                        @RequestParam(required = false, defaultValue = "1900-01-01 00:00:01")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                        @RequestParam(required = false, defaultValue = "2222-01-01 00:00:01")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                        @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                        @RequestParam(defaultValue = "10") @Positive int size) {
        return userCommentService.getComments(userId, rangeStart, rangeEnd, from, size)
                .stream().map(CommentMapper::toCommentDto).collect(Collectors.toList());
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getComment(@PathVariable long userId, @PathVariable long commentId) {
        return CommentMapper.toCommentDto(userCommentService.getComment(userId, commentId));
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable long userId, @PathVariable long commentId) {
        userCommentService.deleteComment(userId, commentId);
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentShortDto addComment(@PathVariable long userId,
                                      @RequestBody NewCommentDto newCommentDto) {
        User user = entityFinder.getUserById(userId);
        Event event = entityFinder.getEventById(newCommentDto.getEventId());
        Comment comment = CommentMapper.toComment(user, event, newCommentDto);
        return CommentMapper.toCommentShortDto(userCommentService.addComment(comment));
    }

    @PatchMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentShortDto updateComment(@PathVariable long userId,
                                         @PathVariable long commentId,
                                         @RequestBody NewCommentDto newCommentDto) {

        return CommentMapper.toCommentShortDto(userCommentService.updateComment(userId,
                commentId, newCommentDto));
    }

}
