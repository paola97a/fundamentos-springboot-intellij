package com.fundamentosspringboot.fundamentos.Bean;

public class MyBeanImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("Hola desde mi implementación propia del Bean");
    }
}
