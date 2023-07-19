package ru.practicum.explore.CONSERREP.service.all;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.explore.CONSERREP.repository.CompilationRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.category.dto.CategoryDto;
import ru.practicum.explore.model.compilation.Compilation;
import ru.practicum.explore.model.compilation.dto.CompilationDto;

import java.util.List;

@Service
@AllArgsConstructor
public class AllCompilationService {

    private CompilationRepository compilationRepository;
    private EntityFinder entityFinder;


    public List<Compilation> getCompilations(Boolean pinned, int from, int size) {
        Pageable pageable = MyPageRequest.of(from,size);
        if(pinned != null){
           return compilationRepository.findAllByPinned(pinned,pageable);
        } else {
          return   compilationRepository.findAllBy(pageable);
        }
    }

    public Compilation getCompilation(long compId) {
        return entityFinder.getCompilationById(compId);
    }
}
