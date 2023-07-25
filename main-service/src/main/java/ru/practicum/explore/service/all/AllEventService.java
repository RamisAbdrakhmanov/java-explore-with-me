package ru.practicum.explore.service.all;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.repository.EventRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.EventSort;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.exception.CustomNotFoundException;
import ru.practicum.explore.model.exception.CustomValidException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class AllEventService {

    private EventRepository eventRepository;
    private EntityFinder entityFinder;

    public Event getEventById(long eventId, long views) {
        Event event = entityFinder.getEventById(eventId);
        if (event.getState() != State.PUBLISHED) {
            throw new CustomNotFoundException(String.format("Event with id=%d was not found", eventId));
        }
        event.setViews(views);
        return eventRepository.save(event);
    }

    public List<Event> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                 LocalDateTime rangeEnd, boolean onlyAvailable,
                                 EventSort sort, int from, int size) {
        if (rangeStart != null && rangeEnd != null) {
            if (rangeStart.isAfter(rangeEnd)) {
                throw new CustomValidException("Start is before End.");
            }
        } else {
            rangeStart = LocalDateTime.now().minusYears(2000);
            rangeEnd = LocalDateTime.now().plusYears(2000);
        }
        Pageable pageable;
        if (sort != null) {
            switch (sort) {
                case EVENT_DATE:
                    pageable = MyPageRequest.of(from, size, "eventDate");
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
            log.info("SQL: ALL : \"findAllForAllWithOnlyAvailable\" : start={}, end={}, text={}, categories={}," +
                    " paid={}", rangeStart, rangeEnd, text, categories, paid);
            return eventRepository.findAllForAllWithOnlyAvailable(text, categories, paid, rangeStart, rangeEnd, pageable);
        } else {
            log.info("SQL: ALL : \"findAllForAllWithoutOnlyAvailable\" : start={}, end={}, text={}, categories={}," +
                    " paid={},sort={}", rangeStart, rangeEnd, text, categories, paid, sort);
            return eventRepository.findAllForAllWithoutOnlyAvailable(text, categories, paid, rangeStart, rangeEnd, pageable);
        }

    }
}
