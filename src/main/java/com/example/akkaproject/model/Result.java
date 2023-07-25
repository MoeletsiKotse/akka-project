package com.example.akkaproject.model;

import lombok.Data;

@Data
public class Result {
    private Status message;

    public Result(Status message) {
        this.message = message;
    }
}
