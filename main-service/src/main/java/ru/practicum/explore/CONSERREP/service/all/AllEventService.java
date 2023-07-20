package ru.practicum.explore.CONSERREP.service.all;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.EventRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.EventSort;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.exception.CustomNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AllEventService {

    private EventRepository eventRepository;
    private EntityFinder entityFinder;

    public Event getEventById(long eventId) {
        Event event = entityFinder.getEventById(eventId);
        if (event.getState() != State.PUBLISHED) {
            throw new CustomNotFoundException(String.format("Event with id=%d was not found", eventId));
        }
        return event;
    }

    public List<Event> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                 LocalDateTime rangeEnd, boolean onlyAvailable,
                                 EventSort sort, int from, int size) {

        Pageable pageable;
        if (sort != null) {
            switch (sort) {
                case EVENT_DATE:
                    pageable = MyPageRequest.of(from, size, "event_date");
                    break;
                case VIEWS:
                    pageable = MyPageRequest.of(from, size, "views");
                    break;
                default:
                    pageable = MyPageRequest.of(from, size);
            }
        } else {
            pageable = MyPageRequest.of(from, size);
        }
        if (onlyAvailable) {
            return eventRepository.findAllForAllWithOnlyAvailable(text, categories, paid, rangeStart, rangeEnd, pageable);
        } else {
            return eventRepository.findAllForAllWithoutOnlyAvailable(text, categories, paid, rangeStart, rangeEnd, pageable);
        }

    }
}
