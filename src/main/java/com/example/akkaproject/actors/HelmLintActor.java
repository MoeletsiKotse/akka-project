package com.example.akkaproject.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class HelmLintActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(String.class, helmName -> {
                    System.out.println(helmName.toString());
                    Process process = Runtime.getRuntime().exec(helmName);
                    process.waitFor();
                    AtomicReference<String> message = new AtomicReference<>("");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    bufferedReader.lines().forEach(m -> message.set(message.get() + m));

                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    errorReader.lines().forEach(m -> message.set(message.get() + m+" "));;
                    log.info("process: {}", process.getOutputStream());
                    log.info("message: {}", message.get());
                    getSender().tell(message.get(), getSelf());
                   log.info("process : {}",process);
                })
                .build();
    }

    public static Props props() {
        return Props.create(HelmLintActor.class);
    }

}
