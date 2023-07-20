package ru.practicum.explore.model.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class NewCompilationDto {

    private boolean pinned;
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;
    private Set<Long> events;

    public NewCompilationDto() {
        events = new HashSet<>();
    }
}
