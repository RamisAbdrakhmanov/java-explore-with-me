package ru.practicum.explore.CONSERREP.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventShortDto;
import ru.practicum.explore.model.event.dto.NewEventDto;
import ru.practicum.explore.model.event.dto.UpdateEventUserRequest;
import ru.practicum.explore.model.participation.dto.ParticipationRequestDto;
import ru.practicum.explore.model.request.EventRequestStatusUpdateRequest;
import ru.practicum.explore.model.request.EventRequestStatusUpdateResult;
import ru.practicum.explore.CONSERREP.service.user.UserEventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/users/{userId}/events")
@AllArgsConstructor
public class UserEventController {

    private UserEventService userEventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventShortDto> getEventsByUserId(@PathVariable long userId,
                                                 @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                 @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Start: USER : \"getEventsByUserId\" : userId={}", userId);
        return userEventService.getEventsByUserId(userId, from, size)
                .stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto addEvent(@PathVariable long userId, @RequestBody NewEventDto newEventDto) {
        log.info("Start: USER : \"addUser\" : userId={}, NewEventDto={}", userId, newEventDto);

        return EventMapper.toEventFullDto(userEventService.addEvent(userId,newEventDto));
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto getEventByUserIdAndEventId(@PathVariable long userId, @PathVariable long eventId) {
        log.info("Start: USER : \"getEventsByUser\" : userId={}, eventId={}", userId, eventId);
        return EventMapper.toEventFullDto(userEventService.getEventByUserIdAndEventId(userId, eventId));
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto updateEvent(@PathVariable long userId, @PathVariable long eventId,
                                    @RequestBody UpdateEventUserRequest updEvent) {
        log.info("Start: USER : \"updateEvent\" : userId={}, eventId={}, updEvent={}", userId, eventId, updEvent);
        return EventMapper.toEventFullDto(userEventService.updateEvent(userId, eventId, updEvent));
    }

 /*   @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequest(@PathVariable long userId, @PathVariable long eventId) {

    }

    @PatchMapping("/{eventId}/requests")
    public List<EventRequestStatusUpdateResult> updateRequest(@PathVariable long userId, @PathVariable long eventId,
                                                              @RequestBody EventRequestStatusUpdateRequest request) {

    }*/


}
