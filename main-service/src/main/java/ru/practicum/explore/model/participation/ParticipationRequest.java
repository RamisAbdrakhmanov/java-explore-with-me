package ru.practicum.explore.model.participation;

import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.request.Status;
import ru.practicum.explore.model.user.User;

import java.time.LocalDateTime;

public class ParticipationRequest {
    private long id;
    private LocalDateTime created;
    private Event event;
    private User requester;
    private Status status;
}
