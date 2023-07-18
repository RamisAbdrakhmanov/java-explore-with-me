package ru.practicum.explore.CONSERREP.service.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.practicum.explore.CONSERREP.repository.EventRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.event.StateActionUser;
import ru.practicum.explore.model.event.dto.NewEventDto;
import ru.practicum.explore.model.event.dto.UpdateEventUserRequest;
import ru.practicum.explore.model.exception.CustomValidException;
import ru.practicum.explore.model.exception.EventStateException;
import ru.practicum.explore.model.participation.dto.ParticipationRequestDto;
import ru.practicum.explore.model.request.EventRequestStatusUpdateRequest;
import ru.practicum.explore.model.request.EventRequestStatusUpdateResult;
import ru.practicum.explore.model.user.User;

import java.util.List;

@Service
@AllArgsConstructor
public class UserEventService {

    private EventRepository eventRepository;
    private EntityFinder entityFinder;

    public List<Event> getEventsByUserId(long userId, int from, int size) {
        Pageable pageable = MyPageRequest.of(from, size);
        return eventRepository.findAllByUserId(userId, pageable);
    }


    public Event addEvent(long userId, NewEventDto newEventDto) {
        User user = entityFinder.getUserById(userId);
        Category category = entityFinder.gerCategoryById(newEventDto.getCategory());
        Event event = EventMapper.toEvent(user, category, newEventDto);
        return eventRepository.save(event);
    }

    public Event getEventByUserIdAndEventId(long userId, long eventId) {

        return entityFinder.getEventByIdAndUserId(userId, eventId);
    }


    public Event updateEvent(long userId, long eventId, UpdateEventUserRequest updEvent) {
        Event event = entityFinder.getEventByIdAndUserId(userId, eventId);
        if (event.getState() != State.PENDING && event.getState() != State.CANCELED) {
            throw new EventStateException("Only pending or canceled events can be changed");
        }
        if (updEvent.getStateAction() != null) {
            if (updEvent.getStateAction() == StateActionUser.SEND_TO_REVIEW) {
                event.setState(State.PENDING);
            } else {
                event.setState(State.CANCELED);
            }
        }
        if (updEvent.getEventDate() != null) {
            event.setEventDate(updEvent.getEventDate());
        }
        if (updEvent.getPaid() != null) {
            event.setPaid(updEvent.getPaid());
        }
        if (updEvent.getAnnotation() != null) {
            event.setAnnotation(updEvent.getAnnotation());
        }
        if (updEvent.getDescription() != null) {
            event.setDescription(updEvent.getDescription());
        }
        if (updEvent.getTitle() != null) {
            event.setTitle(updEvent.getTitle());
        }
        if (updEvent.getRequestModeration() != null) {
            event.setRequestModeration(updEvent.getRequestModeration());
        }
        if (updEvent.getLocation() != null) {
            event.setLat(updEvent.getLocation().getLat());
            event.setLon(updEvent.getLocation().getLon());
        }
        if (updEvent.getParticipantLimit() != null) {
            event.setParticipantLimit(updEvent.getParticipantLimit());
        }

        if (updEvent.getCategory() != 0) {
            Category category = entityFinder.gerCategoryById(updEvent.getCategory());
            event.setCategory(category);
        }

        return eventRepository.save(event);
    }


  /*  public List<ParticipationRequestDto> getRequest(@PathVariable long userId, @PathVariable long eventId) {

    }


    public List<EventRequestStatusUpdateResult> updateRequest(@PathVariable long userId, @PathVariable long eventId,
                                                              @RequestBody EventRequestStatusUpdateRequest request) {

    }*/
}
