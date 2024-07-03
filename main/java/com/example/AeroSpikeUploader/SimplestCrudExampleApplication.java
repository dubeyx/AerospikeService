package com.example.AeroSpikeUploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
//@EnableCaching
public class SimplestCrudExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplestCrudExampleApplication.class, args);
	}

}
