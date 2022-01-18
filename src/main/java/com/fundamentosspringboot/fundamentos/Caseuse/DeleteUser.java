package com.fundamentosspringboot.fundamentos.Caseuse;

import com.fundamentosspringboot.fundamentos.Service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    private UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void remove(Long id) {
        userService.remove(id);
    }
}
