package com.alimentation.simpleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.alimentation")
public class SimpleappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleappApplication.class, args);
		System.out.println("hello nelson");
	}

}
