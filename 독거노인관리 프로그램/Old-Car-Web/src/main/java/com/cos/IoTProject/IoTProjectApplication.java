package com.cos.IoTProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IoTProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoTProjectApplication.class, args);
	}

}
