package com.fundamentosspringboot.fundamentos.Caseuse;

import com.fundamentosspringboot.fundamentos.Entity.User;
import com.fundamentosspringboot.fundamentos.Service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PageUser {
    private UserService userService;

    public PageUser(UserService userService) {
        this.userService = userService;
    }

    public List<User> getPages(int page, int size) {
        return userService.getUsersPages(page,size);
    }
}
