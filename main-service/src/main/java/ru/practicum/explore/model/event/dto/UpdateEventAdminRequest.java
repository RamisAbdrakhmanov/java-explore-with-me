package ru.practicum.explore.model.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.model.event.StateActionAdmin;
import ru.practicum.explore.model.location.Location;
import ru.practicum.explore.validation.OneHoursBeforeNow;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventAdminRequest {
    @Size(min = 20, max = 2000)
    private String annotation;
    private int category;
    @Size(min = 20, max = 7000)
    private String description;
    @OneHoursBeforeNow
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private StateActionAdmin stateAction;
    @Size(min = 2, max = 120)
    private String title;
}
