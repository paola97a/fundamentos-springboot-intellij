package com.fundamentosspringboot.fundamentos.Component;


import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {
    @Override
    public void saludar() {
        System.out.println("Hola desde mi componente dos");
    }
}
