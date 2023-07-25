package ru.practicum.explore.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.service.admin.AdminCompilationService;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.mapper.CompilationMapper;
import ru.practicum.explore.model.compilation.Compilation;
import ru.practicum.explore.model.compilation.dto.CompilationDto;
import ru.practicum.explore.model.compilation.dto.NewCompilationDto;
import ru.practicum.explore.model.compilation.dto.UpdateCompilationDto;
import ru.practicum.explore.model.event.Event;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin/compilations")
@AllArgsConstructor
public class AdminCompilationController {

    private AdminCompilationService adminCompilationService;
    private EntityFinder entityFinder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addCompilation(@Valid @RequestBody NewCompilationDto compilationDto) {
        log.info("Start: ADMIN : \"adminCompilation\" : compilationDto={}", compilationDto);
        Set<Event> events = entityFinder.getEventsForComp(compilationDto.getEvents());
        Compilation compilation = CompilationMapper.toCompilation(compilationDto, events);
        return CompilationMapper.toCompilationDto(adminCompilationService.addCompilation(compilation));
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable long compId) {
        log.info("Start: ADMIN : \"deleteCompilation\" : compId={}", compId);
        adminCompilationService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto updateCompilation(@PathVariable long compId, @Valid @RequestBody UpdateCompilationDto updComp) {
        log.info("Start: ADMIN : \"updateCompilation\" : compId={}, updComp={}", compId, updComp);
        return CompilationMapper.toCompilationDto(adminCompilationService.updateCompilation(compId, updComp));
    }
}
