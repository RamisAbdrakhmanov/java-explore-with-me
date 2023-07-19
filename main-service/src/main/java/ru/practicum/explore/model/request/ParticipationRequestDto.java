package ru.practicum.explore.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestDto {
    private long id;
    private LocalDateTime created;
    private long eventId;
    private long requesterId;
    private Status status;

}
