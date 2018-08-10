package com.spring.akka.spring.akka.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ActorSystemStarter {

    @Autowired
    private ActorSystem system;

    @PostConstruct
    public void initializeAkkaSystem() {
        ActorRef greeter = system.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
                        .props("greetingActor"), "greeter");

    }
}


