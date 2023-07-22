package ru.practicum.explore.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventShortDto;
import ru.practicum.explore.model.event.dto.NewEventDto;
import ru.practicum.explore.model.event.location.Location;
import ru.practicum.explore.model.user.User;
import ru.practicum.explore.model.user.dto.UserShortDto;

@UtilityClass
public class EventMapper {

    public Event toEvent(User user, Category category, NewEventDto eventDto) {
        return new Event(user, category, eventDto.getAnnotation(), eventDto.getDescription(), eventDto.getEventDate(),
                eventDto.getLocation().getLat(), eventDto.getLocation().getLon(), eventDto.getPaid(),
                eventDto.getParticipantLimit(), eventDto.getRequestModeration(), eventDto.getTitle());
    }

    public EventFullDto toEventFullDto(Event event) {
        Location location = new Location(event.getLat(), event.getLon());
        UserShortDto userShortDto = UserMapper.toUserShortDto(event.getInitiator());
        CategoryDto categoryDto = CategoryMapper.toCategoryDto(event.getCategory());
        return new EventFullDto(event.getId(), event.getAnnotation(), categoryDto, event.getConfirmedRequests(),
                event.getCreatedOn(), event.getDescription(), event.getEventDate(), userShortDto, location,
                event.getPaid(), event.getParticipantLimit(), event.getPublishedOn(), event.getRequestModeration(),
                event.getState(), event.getTitle(), event.getViews());
    }

    public EventShortDto toEventShortDto(Event event) {
        UserShortDto userShortDto = UserMapper.toUserShortDto(event.getInitiator());
        CategoryDto categoryDto = CategoryMapper.toCategoryDto(event.getCategory());

        return new EventShortDto(event.getId(), event.getAnnotation(), categoryDto, event.getConfirmedRequests(),
                event.getEventDate(), userShortDto, event.getPaid(), event.getTitle(), event.getViews());

    }

}
