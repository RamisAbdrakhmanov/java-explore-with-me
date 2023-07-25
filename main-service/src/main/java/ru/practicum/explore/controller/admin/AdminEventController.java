package ru.practicum.explore.controller.admin;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.service.admin.AdminEventService;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.UpdateEventAdminDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/admin/events")
@AllArgsConstructor
public class AdminEventController {

    private AdminEventService adminEventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventFullDto> getEvents(@RequestParam(required = false) List<Long> users,
                                        @RequestParam(required = false) List<State> states,
                                        @RequestParam(required = false) List<Long> categories,
                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                        @RequestParam(required = false) LocalDateTime rangeStart,
                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                        @RequestParam(required = false) LocalDateTime rangeEnd,
                                        @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                        @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Start: ADMIN : \"getEvents\" ");
        return adminEventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size)
                .stream().map(EventMapper::toEventFullDto).collect(Collectors.toList());
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto updateEvent(@PathVariable long eventId, @Valid @RequestBody UpdateEventAdminDto updRequest) {
        log.info("Start: ADMIN : \"updateEvent\" : eventId={}, updRequest={}", eventId, updRequest);
        return EventMapper.toEventFullDto(adminEventService.updateEvent(eventId, updRequest));
    }


}
