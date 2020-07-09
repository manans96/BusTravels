package com.manan.busservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.manan.busservice.jpa.repository"})	//same as below for JPA repositories
@ComponentScan(basePackages = {"com.manan.busservice.controller"})				//same as below for REST controllers
@EntityScan(basePackages = {"com.manan.busservice.model"})				//to scan the base package of the entities if main is not present in the parent folder
public class BusserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusserviceApplication.class, args);
	}

}
