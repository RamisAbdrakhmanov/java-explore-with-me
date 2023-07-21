package ru.practicum.explore.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.practicum.explore.dto.EndpointHitDto;
import ru.practicum.explore.dto.ViewStatsDto;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StatsClient {
    private final RestTemplate rest = new RestTemplate();

    public List<ViewStatsDto> getStats(String serverUrl, String start, String end, List<String> uri, boolean unique) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "uri", uri,
                "unique", unique
        );
        log.info("Start: STATS_CLIENT : \"getStats\" : start={}, end={}, uri={}, unique={}", start, end, uri, unique);

        ResponseEntity<List<ViewStatsDto>> exchange = rest.exchange(
                serverUrl + "/stats?start={start}&end={end}&uris={uri}&unique={unique}", HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                }, parameters);
        return exchange.getBody();

    }

    public void addEndpointHit(String serverUrl, EndpointHitDto endpointHitDto) {
        log.info("Start: STATS_CLIENT : \"addEndpointHit\" : endpointHit={}", endpointHitDto);
        rest.postForEntity(serverUrl + "/hit", endpointHitDto, EndpointHitDto.class);
    }

}
