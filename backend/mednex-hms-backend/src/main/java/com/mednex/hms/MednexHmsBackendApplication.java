package com.mednex.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MednexHmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MednexHmsBackendApplication.class, args);
	}

}
