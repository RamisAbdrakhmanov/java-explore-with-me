package ru.practicum.explore.model.event.dto;

import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.location.Location;

import java.time.LocalDateTime;

public class UpdateEventUserRequest {
    private String annotation;
    private int category;
    private String description;
    private LocalDateTime eventDate;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    private State stateAction;
    private String title;
    private Integer views;
}
