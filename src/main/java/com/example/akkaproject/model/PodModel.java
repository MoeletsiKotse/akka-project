package com.example.akkaproject.model;

import akka.actor.ActorRef;
import lombok.Data;

@Data
public class PodModel {
    private String containerName;
    private String containerImage;
    private String deploymentName;
    private String namespace;
    private int replicas;
    private ActorRef actorRef;

    public PodModel(String namespace,String containerName, String containerImage,ActorRef actorRef) {
        this.containerName = containerName;
        this.containerImage = containerImage;
        this.deploymentName = deploymentName;
        this.namespace = namespace;
        this.actorRef = actorRef;
    }
}
