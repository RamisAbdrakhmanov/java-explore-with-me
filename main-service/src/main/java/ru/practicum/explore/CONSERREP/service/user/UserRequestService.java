package ru.practicum.explore.CONSERREP.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.PartReqRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.exception.CustomConflictException;
import ru.practicum.explore.model.exception.CustomNotFoundException;
import ru.practicum.explore.model.request.ParticipationRequest;
import ru.practicum.explore.model.request.Status;
import ru.practicum.explore.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserRequestService {

    private PartReqRepository repository;
    private EntityFinder entityFinder;

    public List<ParticipationRequest> getRequest(long userId) {
        entityFinder.getUserById(userId);
        return repository.findAllByRequesterId(userId);
    }

    public ParticipationRequest addRequest(long userId, long eventId) {
        User user = entityFinder.getUserById(userId);
        Event event = entityFinder.getEventById(eventId);

        if (repository.findByRequesterIdAndEventId(userId, eventId).isPresent()) {
            throw new CustomConflictException("Request created.");
        }

        try {
            entityFinder.getEventByUserIdAndId(userId, eventId);
            throw new CustomConflictException("User cannot be the owner of the event");
        } catch (CustomNotFoundException ignored) {

        }

        if (event.getState() != State.PUBLISHED) {
            throw new CustomConflictException("Event has to be : PUBLISHED.");
        }

        if (event.getParticipantLimit() != 0) {
            if (event.getConfirmedRequests() == event.getParticipantLimit()) {
                throw new CustomConflictException("ParticipantLimit is over.");
            }
        }

        ParticipationRequest pR;
        if (!event.getRequestModeration()) {
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            pR = new ParticipationRequest(0, LocalDateTime.now(), event, user, Status.CONFIRMED);
        } else {
            pR = new ParticipationRequest(0, LocalDateTime.now(), event, user, Status.PENDING);
        }

        return repository.save(pR);
    }

    public ParticipationRequest updateRequest(long userId, long reqId) {
        ParticipationRequest pr = entityFinder.getParticipationRequestByIdAndUserId(reqId, userId);
        pr.setStatus(Status.REJECTED);
        pr.getEvent().setConfirmedRequests( pr.getEvent().getConfirmedRequests() - 1);
        return repository.save(pr);
    }


}
