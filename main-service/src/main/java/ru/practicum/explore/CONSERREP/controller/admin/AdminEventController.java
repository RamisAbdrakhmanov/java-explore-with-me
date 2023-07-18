package ru.practicum.explore.CONSERREP.controller.admin;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.admin.AdminEventService;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.event.State;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.UpdateEventAdminRequest;

import javax.validation.Valid;
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
    public List<EventFullDto> getEvents(@RequestParam(required = false) List<Integer> users,
                                        @RequestParam(required = false) List<State> states,
                                        @RequestParam(required = false) List<Integer> categories,
                                        @RequestParam(required = false)LocalDateTime rangeStart,
                                        @RequestParam(required = false) LocalDateTime rangeEnd,
                                        @RequestParam(defaultValue = "0") int from,
                                        @RequestParam(defaultValue = "10") int size) {
        log.info("Start: ADMIN : \"getEvents\" ");
        return adminEventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size)
                .stream().map(EventMapper::toEventFullDto).collect(Collectors.toList());
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto updateEvent(@PathVariable long eventId, @RequestBody UpdateEventAdminRequest updRequest){
        log.info("Start: ADMIN : \"updateEvent\" : eventId={}, updRequest={}", eventId, updRequest);
        return EventMapper.toEventFullDto(adminEventService.updateEvent(eventId, updRequest));
    }
}
