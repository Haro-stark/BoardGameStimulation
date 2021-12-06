package com.lambdaPractice;

public class HelloWorldGreeting implements Greeting {
    int a;
    @Override
    public void perform() {
        System.out.println("Hello world from HelloWorldGreeting class");
    }
    int checking(){
        return 2;
    }
}
