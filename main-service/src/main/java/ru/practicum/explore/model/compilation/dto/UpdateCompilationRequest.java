package ru.practicum.explore.model.compilation.dto;

import java.util.List;

public class UpdateCompilationRequest {
    private List<Integer> events;
    private boolean pinned;
    private String title;
}
