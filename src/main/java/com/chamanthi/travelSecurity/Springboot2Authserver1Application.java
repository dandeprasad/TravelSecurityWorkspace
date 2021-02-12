package com.chamanthi.travelSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.chamanthi.travelSecurity.*"})
public class Springboot2Authserver1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2Authserver1Application.class, args);
	}

}
