package com.manan.busservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.jpa.repository.UserRepository;
import com.manan.busservice.model.user.User;
import com.manan.busservice.model.user.UserAuth;

@RestController
@RequestMapping(path = "/test")
public class TestController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(path = "/add")
	public void setUser() {
		User user = new User()
				.setFirstName("Manan")
				.setLastName("Sanghvi")
				.setUserName("test96")
				.setEmail("mks8328@rthgire")
				.setPhoneNo("24646464848")
				.setRole("admin")
				.setUserAuth(new UserAuth()
						.setLastUpdate(new Date())
						.setPassword("4fdr4g6ds4dfb")
						);
		
		userRepository.save(user);
			
	}
	
	@GetMapping(path = "/get")
	public @ResponseBody User getUser() {
		return userRepository.findById(1).get();
	}

}
