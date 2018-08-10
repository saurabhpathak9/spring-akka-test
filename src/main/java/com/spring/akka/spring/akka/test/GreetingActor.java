package com.spring.akka.spring.akka.test;

import akka.actor.AbstractActor;
import akka.actor.UntypedActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends AbstractActor {

    private GreetingService greetingService;

    public GreetingActor(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greet.class, message -> {
                    String name = ((Greet) message).getName();
                    getSender().tell(greetingService.greet(name), getSelf());
                })
                .match(Count.class, message -> {
                    for (int i = 0; i < 100; i++) {
                        System.out.println("Count = " + i);
                    }
                })
                .build();
    }

    public static class Greet {

        private String name;
        public Greet(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

    }

    public static class Count {
        private String name;
        public Count(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

    }

}
