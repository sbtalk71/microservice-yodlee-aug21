package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class TurbineAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbineAggregatorApplication.class, args);
	}

}
