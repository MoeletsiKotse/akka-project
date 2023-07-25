package com.example.akkaproject.model;

import lombok.Data;

@Data
public class PodModel {
    private String containerName;
    private String containerImage;
    private String deploymentName;
    private String namespace;
    private int replicas;
}
