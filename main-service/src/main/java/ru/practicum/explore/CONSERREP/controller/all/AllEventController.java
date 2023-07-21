package ru.practicum.explore.CONSERREP.controller.all;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.all.AllEventService;
import ru.practicum.explore.client.StatsClient;
import ru.practicum.explore.dto.EndpointHitDto;
import ru.practicum.explore.dto.ViewStatsDto;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.event.EventSort;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventShortDto;

import javax.servlet.http.HttpServletRequest;
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
public class AllEventController {

    private final String serverUrl;
    private final AllEventService allEventService;
    private final StatsClient statsClient;

    @Autowired
    public AllEventController(AllEventService allEventService, StatsClient statsClient,
                              @Value("${STATS_SERVER_URL}") String serverUrl) {
        this.allEventService = allEventService;
        this.statsClient = statsClient;
        this.serverUrl = serverUrl;
    }

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
                                         @RequestParam(defaultValue = "10") @Positive int size,
                                         HttpServletRequest request) {
        log.info("Start: ALL : \"getEvents\" : text={}, categories={}, paid={}, onlyAvailable={}, sort={}",
                text, categories, paid, onlyAvailable, sort);

        addStats(request);

        return allEventService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size)
                .stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable long eventId, HttpServletRequest request) {
        log.info("Start: ALL : \"getEventById\" : eventId={}", eventId);
        addStats(request);
        List<ViewStatsDto> list = statsClient.getStats(serverUrl, "0000-01-01 00:00:00",
                "9999-12-31 23:59:59",
                List.of(request.getRequestURI()), true);
        if (list.size() != 0) {
            return EventMapper.toEventFullDto(allEventService.getEventById(eventId, list.get(0).getHits()));
        } else {
            return EventMapper.toEventFullDto(allEventService.getEventById(eventId, 1));
        }
    }

    private void addStats(HttpServletRequest request) {
        EndpointHitDto endpointHitDto = new EndpointHitDto("ewm-main-service", request.getRequestURI(),
                request.getRemoteAddr(), LocalDateTime.now());
        statsClient.addEndpointHit(serverUrl, endpointHitDto);
    }
}
