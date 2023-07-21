package ru.practicum.explore.client;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.practicum.explore.dto.EndpointHitDto;
import ru.practicum.explore.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StatsClient {

    private final RestTemplate rest = new RestTemplate();


    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uri, boolean unique) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "uri", uri,
                "unique", unique
        );

        ResponseEntity<List<ViewStatsDto>> exchange = rest.exchange(
                "http://localhost:9090/stats?start={start}&end={end}&uri={uri}&unique={unique}", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                }, parameters);
        return exchange.getBody();

    }

    public void addEndpointHit(EndpointHitDto endpointHit) {
        rest.postForEntity("http://localhost:9090/hit", endpointHit, EndpointHitDto.class);
    }

}
