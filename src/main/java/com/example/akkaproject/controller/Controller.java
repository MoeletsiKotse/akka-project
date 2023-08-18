package com.example.akkaproject.controller;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.PatternsCS;
import com.example.akkaproject.actors.HelmLintActor;
import com.example.akkaproject.actors.HelmListActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

@RestController
@Slf4j
public class Controller {

    private final ActorRef actorRef;
    private final ActorRef actorHelmLintRef;

    //private final ActorRef actorHelmInstallRef;

    @Autowired
    public Controller(ActorSystem actorSystem) {
        this.actorRef = actorSystem.actorOf(Props.create(HelmListActor.class),"helm-chart");
        this.actorHelmLintRef = actorSystem.actorOf(Props.create(HelmLintActor.class),"helm-chart-lint");
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

        CompletionStage<Object> ask = PatternsCS.ask(actorRef, "helm list", 30000);

        return ResponseEntity.ok(ask.thenApplyAsync(response -> response).exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());

    }

    @GetMapping("/get-lint")
    private ResponseEntity<String> getHelmLintChart () throws IOException, InterruptedException {
        CompletionStage<Object> ask = PatternsCS.ask(actorHelmLintRef, "helm lint ./akka-helm-chart", 30000);

        return ResponseEntity.ok(ask.thenApplyAsync(response -> response).exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());

    }

    @DeleteMapping("/helm-unInstall")
    private ResponseEntity<String> unInstallHelmChart (@RequestParam String name) throws IOException, InterruptedException {
        CompletionStage<Object> ask = PatternsCS.ask(actorHelmLintRef,
                "helm uninstall "+name, 30000);

        return ResponseEntity.ok(ask.thenApplyAsync(response -> response).exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());

    }

    @GetMapping("/helm-install")
    private ResponseEntity<String> installHelm (@RequestParam String name) throws IOException, InterruptedException {
        CompletionStage<Object> ask = PatternsCS.ask(actorHelmLintRef,
                "helm install "+name+" ./akka-helm-chart", 30000);

        return ResponseEntity.ok(ask.thenApplyAsync(response -> response).exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());

    }

    @PutMapping("/helm-upgrade")
    private ResponseEntity<String> upgradeHelm (@RequestParam String name) throws IOException, InterruptedException {
        CompletionStage<Object> ask = PatternsCS.ask(actorHelmLintRef,
                "helm upgrade "+name+" ./akka-helm-chart", 30000);

        return ResponseEntity.ok(ask.thenApplyAsync(response -> response).exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());

    }

    @GetMapping("/kubectl-get-all")
    private ResponseEntity<String> kubectlGetAll () throws IOException, InterruptedException {
        CompletionStage<Object> ask = PatternsCS.ask(actorHelmLintRef,
                "kubectl get all", 30000);

        return ResponseEntity.ok(ask.thenApplyAsync(response -> response).exceptionally(
                throwable -> "Error happened"
        ).toCompletableFuture().join().toString());

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
