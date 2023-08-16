package com.example.akkaproject.controller;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.PatternsCS;
import com.example.akkaproject.actors.HelmListActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class Controller {

    private final ActorRef actorRef;
    //private final ActorRef actorRefReciever;

    @Autowired
    public Controller(ActorSystem actorSystem) {
        this.actorRef = actorSystem.actorOf(Props.create(HelmListActor.class),"helm-chart");
        //this.actorRefReciever = actorSystem.actorOf(Props.create(HelmListActor.class),"helm-chart");
    }

    @GetMapping("/ping")
    private ResponseEntity<String> ping (){
        return ResponseEntity.ok("testing........");
    }

    @PostMapping("/helm-create")
    private ResponseEntity<String> createHelm (@RequestParam String name) throws IOException {
        String[] command = {"kubectl apply -f /demo-service.yaml"};
        Process process = Runtime.getRuntime().exec("deployment-file-2.yaml");
        log.info("process: {}",process);
        System.out.println(process);
        return ResponseEntity.ok("Deployed........");
    }

    @GetMapping("/get-helm")
    private ResponseEntity<String> getHelmChart () throws IOException, InterruptedException {
        String[] command = {"helm list"};

        //actorRef.tell(new HelmListActor("helm list"), actorRef);

        CompletionStage<Object> ask = PatternsCS.ask(actorRef, "helm list", 30000);

        return ResponseEntity.ok(ask.thenApplyAsync(response -> response).exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());


/*        return ResponseEntity.ok(ask.thenApplyAsync(response -> "Success").exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());*/
    }

    @GetMapping("/deploy")
    private ResponseEntity<String> deploy () throws IOException {
        String[] command = {"kubectl apply -f /demo-service.yaml"};
        //System.getProperties();
        Process process = Runtime.getRuntime().exec("deployment-file-2.yaml");
        log.info("process: {}",process);
        System.out.println(process);
        return ResponseEntity.ok("Deployed........");
    }
}
