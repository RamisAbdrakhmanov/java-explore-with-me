package ru.practicum.explore.model.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCommentDto {

    @NotBlank
    @Size(min = 1, max = 500)
    private String text;
    @NotNull
    private long eventId;
}
