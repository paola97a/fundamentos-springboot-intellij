package com.fundamentosspringboot.fundamentos.Configuration;

import com.fundamentosspringboot.fundamentos.Caseuse.GetUser;
import com.fundamentosspringboot.fundamentos.Caseuse.GetUserImplement;
import com.fundamentosspringboot.fundamentos.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
