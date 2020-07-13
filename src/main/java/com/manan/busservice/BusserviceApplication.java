package com.manan.busservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusserviceApplication.class, args);
	}

}




/* 
need to add following if main is in a different base package

//@EnableJpaRepositories(basePackages = {"com.manan.busservice.jpa.repository"})	//same as below for JPA repositories
//@ComponentScan(basePackages = {"com.manan.busservice.controller"})				//same as below for REST controllers
//@EntityScan(basePackages = {"com.manan.busservice.model"})				//to scan the base package of the entities if main is not present in the parent folder

*/