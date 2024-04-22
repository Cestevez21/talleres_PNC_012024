package com.cestevez.taller_1.services;


import com.cestevez.taller_1.models.dtos.UserRegisterDTO;
import com.cestevez.taller_1.models.entities.User;

public interface UserService {

    void register(UserRegisterDTO info);
    User findOneById(String id);
    User findOneByEmail(String email);
    User findOneByUsername(String username);
    boolean checkPassword(String password, String password1);
}