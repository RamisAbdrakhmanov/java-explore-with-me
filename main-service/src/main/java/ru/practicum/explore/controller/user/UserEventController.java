package ru.practicum.explore.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.service.user.UserEventService;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.mapper.ParticipationRequestMapper;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventShortDto;
import ru.practicum.explore.model.event.dto.NewEventDto;
import ru.practicum.explore.model.event.dto.UpdateEventUserDto;
import ru.practicum.explore.model.request.assistans.EventRequestStatusUpdateRequest;
import ru.practicum.explore.model.request.assistans.EventRequestStatusUpdateResult;
import ru.practicum.explore.model.request.dto.ParticipationRequestDto;

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
    public EventFullDto addEvent(@PathVariable long userId, @Valid @RequestBody NewEventDto newEventDto) {
        log.info("Start: USER : \"addEvent\" : userId={}, NewEventDto={}", userId, newEventDto);
        return EventMapper.toEventFullDto(userEventService.addEvent(userId, newEventDto));
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
                                    @Valid @RequestBody UpdateEventUserDto updEvent) {
        log.info("Start: USER : \"updateEvent\" : userId={}, eventId={}, updEvent={}", userId, eventId, updEvent);
        return EventMapper.toEventFullDto(userEventService.updateEvent(userId, eventId, updEvent));
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequest(@PathVariable long userId, @PathVariable long eventId) {
        log.info("Start: USER : \"getRequest\" : userId={}, eventId={}", userId, eventId);
        return userEventService.getRequest(userId, eventId)
                .stream().map(ParticipationRequestMapper::toParticipationRequestDto).collect(Collectors.toList());
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult updateRequests(@PathVariable long userId, @PathVariable long eventId,
                                                         @RequestBody EventRequestStatusUpdateRequest request) {
        log.info("Start: USER : \"updateRequest\" : userId={}, eventId={}, request={}", userId, eventId, request);
        return userEventService.updateRequests(userId, eventId, request);
    }

}
