package ru.practicum.explore.model.event;

import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.location.Location;
import ru.practicum.explore.model.user.User;

import java.time.LocalDateTime;

public class Event {
    private long id;
    private String annotation;
    private Category category;
    private long confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private User user;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    private State state;
    private String title;
    private Integer views;
}
