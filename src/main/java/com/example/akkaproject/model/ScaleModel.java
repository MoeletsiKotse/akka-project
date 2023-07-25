package com.example.akkaproject.model;

import lombok.Data;

@Data
public class ScaleModel {
    private int replicas;
    private String namespace;
    private String deploymentName;
}
