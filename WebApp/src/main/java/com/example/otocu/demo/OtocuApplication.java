package com.example.otocu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class OtocuApplication {


	public static void main(String[] args) {
		SpringApplication.run(OtocuApplication.class, args);
	}

}
