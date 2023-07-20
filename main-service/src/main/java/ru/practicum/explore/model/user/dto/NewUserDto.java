package ru.practicum.explore.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    @NotBlank(message = "Name don't have to be BLANK or EMPTY")
    @Size(min = 2, max = 250)
    private String name;
    @NotBlank(message = "Email don't have to be BLANK or EMPTY")
    @Size(min = 6, max = 254)
    @Email(message = "Email must be correct")
    private String email;
}
