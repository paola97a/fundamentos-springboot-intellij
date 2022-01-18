package com.fundamentosspringboot.fundamentos.Configuration;

import com.fundamentosspringboot.fundamentos.Bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

    @Bean
    public MyBean beanOperation(){
        return new MyBeanImplement();
    }
    @Bean
    public MyBean beanOperationTwo(){
        return new MyBeanTwoImplement();
    }
    @Bean
    public MyOperation beanMyOperation(){
        return new MyOperationImplement();
    }
    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }

}
