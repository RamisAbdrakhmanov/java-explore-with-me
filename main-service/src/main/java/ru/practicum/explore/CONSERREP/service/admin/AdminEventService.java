package ru.practicum.explore.CONSERREP.service.admin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.EventRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.event.StateActionAdmin;
import ru.practicum.explore.model.event.StateActionUser;
import ru.practicum.explore.model.event.dto.UpdateEventAdminRequest;
import ru.practicum.explore.model.exception.CustomValidException;
import ru.practicum.explore.model.exception.EventStateException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AdminEventService {

    private EventRepository eventRepository;
    private EntityFinder entityFinder;

    public List<Event> getEvents(List<Integer> users, List<State> states, List<Integer> categories,
                                 LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size) {
        Pageable pageable = MyPageRequest.of(from, size);
        return eventRepository.findAllForAdmin(users, states, categories, rangeStart, rangeEnd, pageable);
    }

    public Event updateEvent(long eventId, UpdateEventAdminRequest updAdm) {
        Event event = entityFinder.getEventByIdAndUserId(eventId);
        if (event.getState() != State.PENDING && event.getState() != State.CANCELED) {
            throw new EventStateException("Cannot publish the event because it's not in the right state: PUBLISHED");
        }
        if (updAdm.getStateAction() != null) {
            if (updAdm.getStateAction() == StateActionAdmin.PUBLISH_EVENT) {
                event.setState(State.PUBLISHED);
                event.setPublishedOn(LocalDateTime.now());
            } else {
                event.setState(State.CANCELED);
            }
        }

        if (updAdm.getEventDate() != null) {
            event.setEventDate(updAdm.getEventDate());
        }
        if (updAdm.getPaid() != null) {
            event.setPaid(updAdm.getPaid());
        }
        if (updAdm.getAnnotation() != null) {
            event.setAnnotation(updAdm.getAnnotation());
        }
        if (updAdm.getDescription() != null) {
            event.setDescription(updAdm.getDescription());
        }
        if (updAdm.getTitle() != null) {
            event.setTitle(updAdm.getTitle());
        }
        if (updAdm.getRequestModeration() != null) {
            event.setRequestModeration(updAdm.getRequestModeration());
        }
        if (updAdm.getLocation() != null) {
            event.setLat(updAdm.getLocation().getLat());
            event.setLon(updAdm.getLocation().getLon());
        }
        if (updAdm.getParticipantLimit() != null) {
            event.setParticipantLimit(updAdm.getParticipantLimit());
        }

        if (updAdm.getCategory() != 0) {
            Category category = entityFinder.gerCategoryById(updAdm.getCategory());
            event.setCategory(category);
        }

        return eventRepository.save(event);
    }

}
