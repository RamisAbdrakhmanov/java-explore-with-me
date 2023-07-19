package ru.practicum.explore.CONSERREP.service.all;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.EventRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.exception.CustomNotFoundException;

@Service
@AllArgsConstructor
public class AllEventService {

    private EventRepository eventRepository;
    private EntityFinder entityFinder;

    public Event getEventById(long eventId) {
        Event event = entityFinder.getEventById(eventId);
        if(event.getState()!= State.PUBLISHED){
           throw new CustomNotFoundException(String.format("Event with id=%d was not found", eventId));
        }
        return event;
    }
}
