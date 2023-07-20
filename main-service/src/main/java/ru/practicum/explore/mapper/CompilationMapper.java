package ru.practicum.explore.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore.model.compilation.Compilation;
import ru.practicum.explore.model.compilation.dto.CompilationDto;
import ru.practicum.explore.model.compilation.dto.NewCompilationDto;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.event.dto.EventShortDto;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class CompilationMapper {

    public Compilation toCompilation(NewCompilationDto ncd, Set<Event> events) {
        return new Compilation(0, ncd.isPinned(), ncd.getTitle(), events);
    }

    public CompilationDto toCompilationDto(Compilation compilation) {
        Set<EventShortDto> shortDtos = compilation.getEvents()
                .stream().map(EventMapper::toEventShortDto).collect(Collectors.toSet());
        return new CompilationDto(compilation.getId(), compilation.isPinned(), compilation.getTitle(), shortDtos);
    }
}
