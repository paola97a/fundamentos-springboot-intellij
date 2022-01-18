package com.fundamentosspringboot.fundamentos.Component;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Component;

@Component

public class ComponentImplement implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("Hola desde mi componente");
    }
}
