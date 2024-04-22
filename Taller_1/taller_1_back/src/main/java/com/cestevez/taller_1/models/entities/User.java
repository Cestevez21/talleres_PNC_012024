package com.cestevez.taller_1.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String username;
    @JsonIgnore
    private String password;
    private String email;


}
