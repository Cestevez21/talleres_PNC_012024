package com.cestevez.taller_1.controllers;

import com.cestevez.taller_1.models.dtos.UserRegisterDTO;
import com.cestevez.taller_1.models.entities.User;
import com.cestevez.taller_1.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cestevez.taller_1.models.dtos.UserLoginDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Api/Auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDTO Info) {
        User userFound = userService.findOneById(Info.getIdentifier());
        if(userFound == null) {
            System.out.println("No fue encontrado en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "Response", "No fue encontrado en la base de datos",
                    "status",HttpStatus.NOT_FOUND));
        }
        if(!userService.checkPassword(Info.getPassword(), userFound.getPassword())) {
            System.out.println("Datos incorrectos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "Response","Datos incorrectos",
                    "status",HttpStatus.UNAUTHORIZED));
        }
        System.out.println("Usuario Logueado");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of(
                "Response","Usuario Logueado",
                "status",HttpStatus.ACCEPTED));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> login(@RequestBody @Valid UserRegisterDTO Info) {
        User userEmail = userService.findOneByEmail(Info.getEmail());
        User userUsername = userService.findOneByUsername(Info.getUsername());

        if(userEmail != null || userUsername != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Response","Usuario/email ya existe",
                    "status",HttpStatus.CONFLICT));
        }

        try {
            userService.register(Info);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Response","Usuario registrado",
                    "status",HttpStatus.CREATED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "Response","Error al registrar el usuario",
                    "status",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
