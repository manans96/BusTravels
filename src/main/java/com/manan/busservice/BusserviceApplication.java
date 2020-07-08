package com.manan.busservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BusserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusserviceApplication.class, args);
	}

}
