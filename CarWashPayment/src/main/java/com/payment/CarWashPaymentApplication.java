package com.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@SpringBootApplication
@EnableEmailTools
public class CarWashPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarWashPaymentApplication.class, args);
	}

}
