package ru.practicum.explore.model.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class NewCommentDto {

    @NotBlank
    @Size(min = 1, max = 500)
    private String text;
    @NotNull
    private long eventId;
}
