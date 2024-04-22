package com.cestevez.taller_1.models.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserRegisterDTO {
    @NotEmpty(message = "username vacío")
    @Size(min = 4, max = 16)
    @Pattern(regexp = "^(?!.*(.).*1)[a-zA-Z0-9_-]{3,16}$")
    private String username;

    @NotEmpty(message = "password vacío")
    @Size(min = 8, max = 32)
    @Pattern(regexp = "^(?!.*(.).*1)(?=.*[!@#$%^&*()-_+=])[a-zA-Z0-9!@#$% ^&*()-_+=]{3,32}$")
    private String password;

    @NotEmpty(message = "email vacío")
    @Email(message = "Formato de email incorrecto")
    private String email;
}
