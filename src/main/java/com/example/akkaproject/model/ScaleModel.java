package com.example.akkaproject.model;

import lombok.Data;

@Data
public class ScaleModel {
    private int replicas;
    private String namespace;
    private String deploymentName;

    public ScaleModel(int replicas, String namespace, String deploymentName) {
        this.replicas = replicas;
        this.namespace = namespace;
        this.deploymentName = deploymentName;
    }
}
