package ru.practicum.explore.model.compilation.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NewCompilationDto {
    private List<Integer> events;
    private boolean pinned;
    @NotNull
    private String title;
}
