package com.fundamentosspringboot.fundamentos.Caseuse;

import com.fundamentosspringboot.fundamentos.Entity.User;
import com.fundamentosspringboot.fundamentos.Service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
