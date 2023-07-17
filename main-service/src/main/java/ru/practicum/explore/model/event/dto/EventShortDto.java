package ru.practicum.explore.model.event.dto;

import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.model.user.dto.UserShortDto;

import java.time.LocalDateTime;

public class EventShortDto {
    private long id;
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private boolean paid;
    private String title;
    private Integer views;
}
