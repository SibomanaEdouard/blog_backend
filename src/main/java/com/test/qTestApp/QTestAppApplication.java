package com.test.qTestApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QTestAppApplication {
//this is teh entry point of  the application
	public static void main(String[] args) {
		SpringApplication.run(QTestAppApplication.class, args);
		System.out.println("application is running ....");
	}

}
