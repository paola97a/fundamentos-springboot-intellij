package com.fundamentosspringboot.fundamentos.Caseuse;

import com.fundamentosspringboot.fundamentos.Entity.User;
import com.fundamentosspringboot.fundamentos.Service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> gelAll() {
        return userService.getAllUsers();
    }
}
