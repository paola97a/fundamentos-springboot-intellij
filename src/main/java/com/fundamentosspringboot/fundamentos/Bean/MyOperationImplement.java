package com.fundamentosspringboot.fundamentos.Bean;

public class MyOperationImplement implements MyOperation{
    @Override
    public int sum(int number) {
        return number+1;
    }
}
