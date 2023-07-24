package ru.practicum.explore.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.service.admin.AdminCommentService;
import ru.practicum.explore.mapper.CommentMapper;
import ru.practicum.explore.model.comment.dto.CommentShortDto;
import ru.practicum.explore.model.comment.dto.NewCommentDto;

@Slf4j
@RestController
@RequestMapping("/admin/comments")
@AllArgsConstructor
public class AdminCommentController {

    private AdminCommentService adminCommentService;


    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentShortDto updateComment(@PathVariable long commentId, @RequestBody NewCommentDto newCommentDto) {
        return CommentMapper.toCommentShortDto(adminCommentService.updateComment(commentId, newCommentDto));
    }
}
