package com.spring.akka.spring.akka.test;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("spring-akka-test");
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
        return system;
    }

}
