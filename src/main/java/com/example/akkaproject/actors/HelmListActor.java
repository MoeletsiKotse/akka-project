package com.example.akkaproject.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
public class HelmListActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().
/*                match(String.class,helmName -> {
                    System.out.println(helmName);
                    context().sender();
                }). */match(String.class,helmName -> {
                    System.out.println(helmName.toString());
                    Process process = Runtime.getRuntime().exec("helm list");
                    process.waitFor();
    AtomicReference<String> message = new AtomicReference<>("");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    bufferedReader.lines().forEach(m -> message.set(message.get()+m+" "));

                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    errorReader.lines().forEach(System.out::println);
                    log.info("process: {}",process.getOutputStream());
    log.info("message: {}",message.get());
            getSender().tell(message.get(), getSelf());
    System.out.println(process);
                })
                .build();
    }

    public record GetName(String name) {
    }

    public static class ChatMessage {
        public String getMessage() {
            return message;
        }

        public final String message;

        public ChatMessage(String message) {
            this.message = message;
        }
    }
    public static Props props(){
        return Props.create(HelmListActor.class);
    }

   /* public interface Command{}

    public record HelmChat(akka.actor.typed.ActorRef<Confirmation> replyTo, String eventName) implements Command {
    }


    public record Confirmation(String message) {
    }

    public static Behavior<Command> create(){
        return Behaviors.receive(Command.class)
                .onMessage(HelmChat.class, (context ) -> {
                        String results = "Helm list";
                        context.replyTo.tell(new Confirmation(results));
                        return Behaviors.same();
    }).build();
    }

    public class HemlChartImpl{
        public void getList(){
            ActorSystem system =
                    ActorSystem.create();

            ActorRef<HelmListActor.Confirmation> confirmationActorRef =
                    system.systemActorOf(
                            Behaviors.receiveMessage(conformation ->
                            {
                                System.out.println("Recieved");
                                return Behaviors.same();
                            }),"confirmationActorRef");
            system.tell
        }
    }*/


}
