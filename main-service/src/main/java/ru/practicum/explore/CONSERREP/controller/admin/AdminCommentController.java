package ru.practicum.explore.CONSERREP.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.admin.AdminCommentService;
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
    public CommentShortDto updateComment(@PathVariable long commentId, @RequestBody NewCommentDto newCommentDto){
        return CommentMapper.toCommentShortDto(adminCommentService.updateComment(commentId, newCommentDto));
    }
}
