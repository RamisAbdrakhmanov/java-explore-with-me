package ru.practicum.explore.CONSERREP.controller.all;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore.CONSERREP.service.all.AllEventService;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventShortDto;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Valid
@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class AllEventController {

    private AllEventService allEventService;

    @GetMapping
    public List<EventShortDto> getEvents() {
        log.info("Start: ALL : \"getEvents\" : eventId=");
        return null;
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable long eventId) {
        log.info("Start: ALL : \"getEventById\" : eventId={}",eventId);
        return EventMapper.toEventFullDto(allEventService.getEventById(eventId));
    }
}
