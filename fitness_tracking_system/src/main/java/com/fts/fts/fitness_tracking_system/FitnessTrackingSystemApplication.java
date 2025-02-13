package com.fts.fts.fitness_tracking_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.fts.fts.fitness_tracking_system.mapper")
public class FitnessTrackingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessTrackingSystemApplication.class, args);
	}

}
