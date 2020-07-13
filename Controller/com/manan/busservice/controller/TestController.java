package com.manan.busservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;
import com.manan.busservice.service.user.UserService;
import com.manan.busservice.utility.DateUtils;

@RestController
@RequestMapping("/test")
public class TestController {
	
	UserService userService;
	
	@Autowired
	public TestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public void register() {
		
		User user = new User()
				.setFirstName("Manan")
				.setLastName("Sanghvi")
				.setUserName("test96")
				.setEmail("mks8328@rthgire")
				.setPhoneNo("24646464848")
				.setRole("admin");
		
		UserAuth userAuth = new UserAuth()
				.setPassword("neiuewn48fi")
				.setLastUpdate(DateUtils.today());
		
		userService.signup(user, userAuth);
		
//		userRepository.save(user);
			
	}
	
	@GetMapping("/get")
	public @ResponseBody User getUser() {

		return userService.findUser("test96");
	}

}
