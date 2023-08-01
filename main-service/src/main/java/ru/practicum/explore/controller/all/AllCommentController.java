package ru.practicum.explore.controller.all;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.practicum.explore.mapper.CommentMapper;
import ru.practicum.explore.model.comment.dto.CommentShortDto;
import ru.practicum.explore.service.all.AllCommentService;


import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/comments/{eventId}")
@AllArgsConstructor
public class AllCommentController {

    private AllCommentService allCommentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentShortDto> getComments(@PathVariable long eventId,
                                             @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                             @RequestParam(defaultValue = "10") @Positive int size) {
        return allCommentService.getComments(eventId, from, size)
                .stream().map(CommentMapper::toCommentShortDto).collect(Collectors.toList());
    }

}
