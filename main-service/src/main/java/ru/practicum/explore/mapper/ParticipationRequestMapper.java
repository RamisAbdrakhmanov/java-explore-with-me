package ru.practicum.explore.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore.model.request.ParticipationRequest;
import ru.practicum.explore.model.request.ParticipationRequestDto;

@UtilityClass
public class ParticipationRequestMapper {

    public ParticipationRequestDto toParticipationRequestDto(ParticipationRequest pr){
        return new ParticipationRequestDto(pr.getId(), pr.getCreated(),pr.getEvent().getId(),
                pr.getRequester().getId(),pr.getStatus());
    }
}
