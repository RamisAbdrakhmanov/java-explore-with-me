package ru.practicum.explore.model.event.dto;

import ru.practicum.explore.model.location.Location;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NewEventDto {
    @NotNull
    private String annotation;
    @NotNull
    private long category;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime eventDate;
    @NotNull
    private Location location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    @NotNull
    private String title;
}
