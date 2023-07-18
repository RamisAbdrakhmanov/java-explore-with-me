package ru.practicum.explore.common;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explore.CONSERREP.repository.CategoryRepository;
import ru.practicum.explore.CONSERREP.repository.EventRepository;
import ru.practicum.explore.CONSERREP.repository.UserRepository;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.exception.CustomNotFoundException;
import ru.practicum.explore.model.user.User;

@Component
@AllArgsConstructor
public class EntityFinder {

    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;

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

    public Event getEventByIdAndUserId(long userId, long eventId) {
        getUserById(userId);
        return eventRepository.findByIdAndUserId(eventId, userId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Event with id=%d was not found", eventId)));
    }

    public Event getEventByIdAndUserId(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format("Event with id=%d was not found", eventId)));
    }
}
