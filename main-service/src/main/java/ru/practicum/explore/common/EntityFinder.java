package ru.practicum.explore.common;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explore.CONSERREP.repository.*;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.compilation.Compilation;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.comment.Comment;
import ru.practicum.explore.model.exception.CustomNotFoundException;
import ru.practicum.explore.model.request.ParticipationRequest;
import ru.practicum.explore.model.user.User;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class EntityFinder {

    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;
    private CompilationRepository compilationRepository;
    private PartReqRepository partReqRepository;
    private CommentRepository commentRepository;

    public Category gerCategoryById(long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Category with id=%d was not found", catId)));
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("User with id=%d was not found", id)));
    }

    public Event getEventByUserIdAndId(long userId, long eventId) {
        getUserById(userId);
        return eventRepository.findByIdAndInitiatorId(eventId, userId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Event with id=%d was not found", eventId)));
    }

    public Event getEventById(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Event with id=%d was not found", eventId)));
    }

    public Compilation getCompilationById(long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Compilation with id=%d was not found", compId)));
    }

    public ParticipationRequest getParticipationRequestByIdAndUserId(long reqId, long userId) {
        getUserById(userId);
        return partReqRepository.findByIdAndRequesterId(reqId, userId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Request with id=%d was not found", reqId)));
    }

    public Comment getCommentByIdAndUserId(long commentId, long userId) {
        getUserById(userId);
        return commentRepository.findByIdAndAuthorId(commentId, userId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Comment with id=%d for user_id=%d was not found", commentId, userId)));
    }

    public  Comment getCommentById(long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Comment with id=%d was not found", commentId)));
    }

    public Set<Event> getEventsForComp(Set<Long> eventsIds) {
        return eventRepository.findAllByIdIn(eventsIds);
    }


    public List<Event> getEventsByCatId(long catId) {
        return eventRepository.findAllByCategoryId(catId);
    }
}
