package com.example.btree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BtreeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtreeAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(BTreeService service) {
		return args -> {
			System.out.println("Spring Boot Application is running!");
		};
	}

}
