package ru.practicum.explore.CONSERREP.controller.all;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.all.AllEventService;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.event.EventSort;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventShortDto;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class AllEventController {

    private AllEventService allEventService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(required = false) String text,
                                         @RequestParam(required = false) List<Long> categories,
                                         @RequestParam(required = false) Boolean paid,
                                         @Future
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                         @RequestParam(required = false) LocalDateTime rangeStart,
                                         @Future
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                         @RequestParam(required = false) LocalDateTime rangeEnd,
                                         @RequestParam(defaultValue = "false") boolean onlyAvailable,
                                         @RequestParam(required = false) EventSort sort,
                                         @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                         @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Start: ALL : \"getEvents\" : text={}, categories={}, paid={}, onlyAvailable={}, sort={}",
                text, categories, paid, onlyAvailable, sort);
        return allEventService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size)
                .stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable long eventId) {
        log.info("Start: ALL : \"getEventById\" : eventId={}", eventId);
        return EventMapper.toEventFullDto(allEventService.getEventById(eventId));
    }
}
