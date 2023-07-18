package ru.practicum.explore.model.participation.dto;

import ru.practicum.explore.model.request.Status;

import java.time.LocalDateTime;

public class ParticipationRequestDto {
    private long id;
    private LocalDateTime created;
    private long eventId;
    private long requesterId;
    private Status status;

}
