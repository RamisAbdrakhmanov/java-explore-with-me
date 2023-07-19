package ru.practicum.explore.CONSERREP.controller.all;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.all.AllCompilationService;
import ru.practicum.explore.mapper.CompilationMapper;
import ru.practicum.explore.mapper.EventMapper;
import ru.practicum.explore.model.compilation.dto.CompilationDto;
import ru.practicum.explore.model.event.dto.EventFullDto;
import ru.practicum.explore.model.event.dto.EventShortDto;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/compilations")
@AllArgsConstructor
public class AllCompilationController {

    private AllCompilationService allCompilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(required = false) Boolean pinned,
                                                @RequestParam(defaultValue = "0") int from,
                                                @RequestParam(defaultValue = "10") int size) {
        log.info("Start: ALL : \"getCompilations\" : ");
        return allCompilationService.getCompilations(pinned,from,size)
                .stream().map(CompilationMapper::toCompilationDto).collect(Collectors.toList());
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilation(@PathVariable long compId) {
        log.info("Start: ALL : \"getCompilation\" : compId={}", compId);
        return CompilationMapper.toCompilationDto(allCompilationService.getCompilation(compId));
    }
}
