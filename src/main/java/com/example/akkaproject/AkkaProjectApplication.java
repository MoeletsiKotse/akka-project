package com.example.akkaproject;

import kamon.Kamon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AkkaProjectApplication {

	public static void main(String[] args) throws IOException {

		Kamon.init();

		SpringApplication.run(AkkaProjectApplication.class, args);
	}

}
