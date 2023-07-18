package ru.practicum.explore.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    @NotBlank
    @Size(min = 2, max = 250)
    private String name;
    @NotBlank
    @Size(min = 6, max = 254)
    private String email;
}
