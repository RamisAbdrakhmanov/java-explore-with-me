package ru.practicum.explore.model.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class UpdateCompilationDto {

    private Boolean pinned;
    @Size(min = 1, max = 50)
    private String title;
    @NotNull
    private Set<Long> events;

    public UpdateCompilationDto() {
        events = new HashSet<>();
    }
}
