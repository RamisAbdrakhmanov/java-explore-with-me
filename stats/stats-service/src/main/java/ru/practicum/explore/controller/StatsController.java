package ru.practicum.explore.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.dto.EndpointHitDto;
import ru.practicum.explore.dto.ViewStatsDto;
import ru.practicum.explore.mapper.EndpointHitMapper;
import ru.practicum.explore.mapper.ViewStatsMapper;
import ru.practicum.explore.model.EndpointHit;
import ru.practicum.explore.service.StatsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class StatsController {

    private StatsService statsService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEndpointHit(@RequestBody EndpointHitDto endpointHitDto) {
        log.info("Start: STATS_SERVICE : \"addEndpointHit\" : endpointHit={}", endpointHitDto);
        EndpointHit endpointHit = EndpointHitMapper.toEndpointHit(endpointHitDto);
        statsService.addEndpointHit(endpointHit);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewStatsDto> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                       @RequestParam(required = false) List<String> uris,
                                       @RequestParam(defaultValue = "false") boolean unique) {
        log.info("Start: STATS_CLIENT : \"getStats\" : start={}, end={}, uri={}, unique={}", start, end, uris, unique);
        return statsService.getStats(start, end, uris, unique)
                .stream().map(ViewStatsMapper::toViewStatsDto).collect(Collectors.toList());
    }

}
