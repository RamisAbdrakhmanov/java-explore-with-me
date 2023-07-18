package ru.practicum.explore.model.compilation.dto;

import ru.practicum.explore.model.event.dto.EventShortDto;

import java.util.List;

public class CompilationDto {
    private List<EventShortDto> events;
    private long id;
    private boolean pinned;
    private String title;
}
