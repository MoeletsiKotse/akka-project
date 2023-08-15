package com.example.akkaproject.config;


import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {
    @Bean
    public ActorSystem actorSystem(){
        return ActorSystem.create();
    }
}
