package com.pathus.covid19bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Covid19botGuineaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19botGuineaApplication.class, args);
	}
}
