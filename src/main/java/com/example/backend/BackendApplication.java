package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {
	private static final int numberRows = 5;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(numberRows);
	}


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
