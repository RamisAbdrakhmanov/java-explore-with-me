package ru.practicum.explore.model.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.model.location.Location;
import ru.practicum.explore.validation.TwoHoursBeforeNow;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {
    @NotBlank
    @Size(min = 20,max = 2000)
    private String annotation;
    @NotNull
    private long category;
    @NotBlank
    @Size(min = 20, max = 7000)
    private String description;
    @NotNull
    @TwoHoursBeforeNow
    private LocalDateTime eventDate;
    @NotNull
    private Location location;
    private Boolean paid;
    private int participantLimit;
    private Boolean requestModeration;
    @NotNull
    @Size(min = 3, max = 120)
    private String title;
}
