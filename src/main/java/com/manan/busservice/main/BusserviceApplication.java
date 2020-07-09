package com.manan.busservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan(basePackages = {"com.manan.busservice.model"})				//to scan the base package of the entities if main is not present in the parent folder
public class BusserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusserviceApplication.class, args);
	}

}
