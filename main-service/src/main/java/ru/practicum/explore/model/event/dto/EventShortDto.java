package ru.practicum.explore.model.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.model.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDto {
    private long id;
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Integer views;
}
