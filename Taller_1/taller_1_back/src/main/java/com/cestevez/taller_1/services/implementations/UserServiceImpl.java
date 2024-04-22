package com.cestevez.taller_1.services.implementations;

import com.cestevez.taller_1.models.dtos.UserRegisterDTO;
import com.cestevez.taller_1.models.entities.User;
import com.cestevez.taller_1.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("douglashdezt", "12345678Aa!","dohernandez@uca.edu.sv"));
        users.add(new User("naldana", "12345678Aa!","naldana@uca.edu.sv"));
        users.add(new User("ecalderon", "12345678Aa!","ealdana@uca.edu.sv"));
        users.add(new User("enxel", "12345678Aa!","earaujo@uca.edu.sv"));
        users.add(new User("evarela", "12345678Aa!","evarela@uca.edu.sv"));
        users.add(new User("armandoCodigos", "12345678Aa!","rcanizales@uca.edu.sv"));
    }

    @Override
    public void register(UserRegisterDTO info) {
        User newUser = new User(
                info.getUsername(),
                info.getPassword(),
                info.getEmail()
        );

        users = Stream.concat(users.stream(), Stream.of(newUser))
                .collect(Collectors.toList());
    }

    @Override
    public User findOneById(String id) {
        return users.stream()
                .filter(u -> ( u.getUsername().equals(id) || u.getEmail().equals(id) ))
                .findAny()
                .orElse(null);
    }

    @Override
    public User findOneByEmail(String email) {
        return users.stream()
                .filter(u -> ( u.getEmail().equals(email)) )
                .findAny()
                .orElse(null);
    }
    @Override
    public User findOneByUsername(String username) {
        return users.stream()
                .filter(u -> ( u.getUsername().equals(username)) )
                .findAny()
                .orElse(null);
    }
    public boolean checkPassword(String password, String confirmPassword) {
        return Objects.equals(password, confirmPassword);
    }
}
