package ru.practicum.explore.model.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.model.event.dto.EventShortDto;
import ru.practicum.explore.model.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private EventShortDto event;
    private UserShortDto author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
}
