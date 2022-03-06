package com.spring.akka.spring.akka.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@SpringBootTest
public class SpringAkkaTestApplicationTests {

    @Autowired
    private ActorSystem system;

    @Test
    public void contextLoads() throws Exception {
        ActorRef testActor = system.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
                        .props("greetingActor"), "testActor");

        testActor.tell(new GreetingActor.Count("Saurabh"), testActor);
        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<Object> result = ask(testActor, new GreetingActor.Greet("Saurabh"), timeout);
        Assert.assertEquals("Hello, Saurabh", Await.result(result, duration));
    }

}
