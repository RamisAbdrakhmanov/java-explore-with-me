package ru.practicum.explore.model.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.model.event.location.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {
    @NotBlank(message = "Annotation don't have to be BLANK or EMPTY")
    @Size(min = 20, max = 2000)
    private String annotation;
    @NotNull(message = "Category don't have to be NULL")
    private long category;
    @NotBlank(message = "Description don't have to be BLANK or EMPTY")
    @Size(min = 20, max = 7000)
    private String description;
    @NotNull(message = "EventDate don't have to be NULL")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    @NotNull(message = "Location don't have to be NULL")
    private Location location;
    private Boolean paid;
    private int participantLimit;
    private Boolean requestModeration;
    @NotNull(message = "Title don't have to be NULL")
    @Size(min = 3, max = 120)
    private String title;
}
