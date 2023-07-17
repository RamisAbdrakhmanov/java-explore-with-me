package ru.practicum.explore.model.event.dto;

import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.location.Location;
import ru.practicum.explore.model.user.dto.UserShortDto;

import java.time.LocalDateTime;

public class EventFullDto {
    private long id;
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    private State state;
    private String title;
    private Integer views;
}
