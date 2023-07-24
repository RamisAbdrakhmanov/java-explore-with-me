package ru.practicum.explore.service.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.mapper.ParticipationRequestMapper;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.event.StateActionUser;
import ru.practicum.explore.model.event.dto.NewEventDto;
import ru.practicum.explore.model.event.dto.UpdateEventUserDto;
import ru.practicum.explore.model.exception.CustomConflictException;
import ru.practicum.explore.model.exception.CustomForbiddenException;
import ru.practicum.explore.model.exception.CustomNotFoundException;
import ru.practicum.explore.model.exception.CustomValidException;
import ru.practicum.explore.model.request.ParticipationRequest;
import ru.practicum.explore.model.request.Status;
import ru.practicum.explore.model.request.assistans.EventRequestStatusUpdateRequest;
import ru.practicum.explore.model.request.assistans.EventRequestStatusUpdateResult;
import ru.practicum.explore.model.user.User;
import ru.practicum.explore.repository.EventRepository;
import ru.practicum.explore.repository.PartReqRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserEventService {

    private EventRepository eventRepository;
    private PartReqRepository partReqRepository;
    private EntityFinder entityFinder;

    public List<Event> getEventsByUserId(long userId, int from, int size) {
        Pageable pageable = MyPageRequest.of(from, size);
        return eventRepository.findAllByInitiatorId(userId, pageable);
    }


    public Event addEvent(long userId, NewEventDto newEventDto) {
        if (newEventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new CustomValidException("Field: eventDate. " +
                    "Error: должно содержать дату, которая еще не наступила. " +
                    "Value: " + newEventDto.getEventDate().toString());
        }
        User user = entityFinder.getUserById(userId);
        Category category = entityFinder.gerCategoryById(newEventDto.getCategory());
        Event event = EventMapper.toEvent(user, category, newEventDto);
        return eventRepository.save(event);
    }

    public Event getEventByUserIdAndEventId(long userId, long eventId) {

        return entityFinder.getEventByUserIdAndId(userId, eventId);
    }


    public Event updateEvent(long userId, long eventId, UpdateEventUserDto updEvent) {
        Event event = entityFinder.getEventByUserIdAndId(userId, eventId);

        if (event.getState() != State.PENDING && event.getState() != State.CANCELED) {
            throw new CustomForbiddenException("Only pending or canceled events can be changed");
        }
        if (updEvent.getStateAction() != null) {
            if (updEvent.getStateAction() == StateActionUser.SEND_TO_REVIEW) {
                event.setState(State.PENDING);
            } else {
                event.setState(State.CANCELED);
            }
        }
        if (updEvent.getEventDate() != null) {
            if (updEvent.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
                throw new CustomValidException("Field: eventDate. " +
                        "Error: должно содержать дату, которая еще не наступила. " +
                        "Value: " + updEvent.getEventDate().toString());
            }
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

        if (updEvent.getCategory() != null) {
            Category category = entityFinder.gerCategoryById(updEvent.getCategory());
            event.setCategory(category);
        }

        return eventRepository.save(event);
    }

    public List<ParticipationRequest> getRequest(long userId, long eventId) {
        entityFinder.getUserById(userId);
        entityFinder.getEventById(eventId);

        return partReqRepository.findAllByEventId(eventId);
    }

    public EventRequestStatusUpdateResult updateRequests(long userId, long eventId,
                                                         EventRequestStatusUpdateRequest request) {
        Event event = entityFinder.getEventByUserIdAndId(userId, eventId);

        if (event.getState() != State.PUBLISHED) {
            throw new CustomConflictException("Event has to be : PUBLISHED.");
        }
        int counter = 0;
        try {

            for (int i = 0; i < request.getRequestIds().size(); i++) {
                long l = request.getRequestIds().get(i);
                ParticipationRequest pr = partReqRepository.findById(l)
                        .orElseThrow(() -> new CustomNotFoundException(String
                                .format("Request with id=%d was not found", l)));
                if (pr.getStatus() != Status.PENDING) {
                    throw new CustomConflictException("Request must have status PENDING");
                }
                if (event.getParticipantLimit() != 0) {
                    if (event.getConfirmedRequests() == event.getParticipantLimit()) {
                        counter = i;
                        throw new CustomConflictException("ParticipantLimit is over.");
                    }
                }

                pr.setStatus(request.getStatus());

                if (request.getStatus() == Status.CONFIRMED) {
                    event.setConfirmedRequests(event.getConfirmedRequests() + 1);
                }
                partReqRepository.save(pr);
            }
        } catch (CustomConflictException e) {
            throw new CustomConflictException(e.getMessage());
        } finally {
            for (int i = counter; i < request.getRequestIds().size(); i++) {
                long l = request.getRequestIds().get(i);
                ParticipationRequest pr = partReqRepository.findById(l)
                        .orElseThrow(() -> new CustomNotFoundException(String
                                .format("Request with id=%d was not found", l)));
                if (pr.getStatus() == Status.PENDING) {
                    pr.setStatus(Status.REJECTED);
                }
                partReqRepository.save(pr);
            }
        }

        List<ParticipationRequest> confirmedRequests = partReqRepository
                .findAllByEventIdAndStatus(eventId, Status.CONFIRMED);
        List<ParticipationRequest> rejectedRequests = partReqRepository
                .findAllByEventIdAndStatus(eventId, Status.REJECTED);

        return new EventRequestStatusUpdateResult(
                confirmedRequests.stream()
                        .map(ParticipationRequestMapper::toParticipationRequestDto).collect(Collectors.toList()),
                rejectedRequests.stream()
                        .map(ParticipationRequestMapper::toParticipationRequestDto).collect(Collectors.toList())
        );

    }
}
