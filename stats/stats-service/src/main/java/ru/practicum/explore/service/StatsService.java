package ru.practicum.explore.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.dao.StatsRepository;
import ru.practicum.explore.exception.CustomValidException;
import ru.practicum.explore.model.EndpointHit;
import ru.practicum.explore.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StatsService {
    private StatsRepository statsRepository;

    public void addEndpointHit(EndpointHit endpointHit) {
        statsRepository.save(endpointHit);
    }

    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {

        if (start.isAfter(end)) {
            throw new CustomValidException("Start mist be before end.");
        }
        if (unique) {
            return statsRepository.findUniqueViewStats(start, end, uris);
        } else {
            return statsRepository.findViewStats(start, end, uris);
        }
    }
}
