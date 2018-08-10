package com.spring.akka.spring.akka.test;

import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    public String greet(String name) {
        return "Hello, " + name;
    }

}
