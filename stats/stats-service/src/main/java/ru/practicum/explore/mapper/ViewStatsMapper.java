package ru.practicum.explore.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore.dto.ViewStatsDto;
import ru.practicum.explore.model.ViewStats;

@UtilityClass
public class ViewStatsMapper {

    public static ViewStatsDto toViewStatsDto(ViewStats viewStats) {
        return new ViewStatsDto(viewStats.getApp(), viewStats.getUri(), viewStats.getHits());
    }
}
