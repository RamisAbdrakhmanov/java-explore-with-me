package ru.practicum.explore.CONSERREP.service.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.CompilationRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.model.compilation.Compilation;
import ru.practicum.explore.model.compilation.dto.UpdateCompilationDto;
import ru.practicum.explore.model.event.Event;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminCompilationService {

    private CompilationRepository compilationRepository;
    private EntityFinder entityFinder;

    public Compilation addCompilation(Compilation compilation) {
        return compilationRepository.save(compilation);
    }

    public void deleteCompilation(long compId) {
        entityFinder.getCompilationById(compId);
        compilationRepository.deleteById(compId);
    }

    public Compilation updateCompilation(long compId, UpdateCompilationDto updComp) {
        Compilation compilation = entityFinder.getCompilationById(compId);
        if(updComp.getEvents() != null ){
            Set<Event> events = entityFinder.getEventsForComp(updComp.getEvents());
            compilation.setEvents(events);
        }
        if(updComp.getPinned() != null ){
            compilation.setPinned(updComp.getPinned());
        }
        if(updComp.getTitle() != null ){
            compilation.setTitle(updComp.getTitle());
        }
        return compilationRepository.save(compilation);
    }
}
