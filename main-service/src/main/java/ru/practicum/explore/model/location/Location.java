package ru.practicum.explore.model.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @NotNull
    private float lat;
    @NotNull
    private float lon;
}
