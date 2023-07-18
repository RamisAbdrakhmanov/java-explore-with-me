package ru.practicum.explore.model.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.location.Location;
import ru.practicum.explore.model.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Boolean paid;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    private Integer views;
}
