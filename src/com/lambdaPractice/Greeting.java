package com.lambdaPractice;

@FunctionalInterface
public interface Greeting {
    default int add(){
        return 2+3;
    }
    void perform();

}
