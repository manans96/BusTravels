package com.manan.busservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;
import com.manan.busservice.service.operator.BusOperatorService;
import com.manan.busservice.service.operator.BusService;
import com.manan.busservice.service.user.UserService;
import com.manan.busservice.utility.DateUtils;

@RestController
@RequestMapping("/test")
public class TestController {
	
	UserService userService;
	BusOperatorService busOperatorService;
	BusService busService;
	
	@Autowired
	public TestController(UserService userService, BusOperatorService busOperatorService, BusService busService) {
		this.userService = userService;
		this.busOperatorService = busOperatorService;
		this.busService = busService;
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
	public @ResponseBody BusOperator getUser() {

//		return userService.findUser("test96");
		return busOperatorService.viewBusOperator("AAC123");
	}
	
	@PostMapping("/addoperator")
	public void newOperator() {
		
		User operator = userService.findUser("test96");
		
		BusOperator busOperator = new BusOperator()
				.setOperator(operator)
				.setOperatorCode("AAC123")
				.setOperatorDetails("Best Service!")
				.setOperatorName("MyOperator");
		
		busOperatorService.addNewOperator(busOperator);
		
	}
	
	@PostMapping("/addbus")
	public void newBus() {
		
		BusOperator busOperator = busOperatorService.viewBusOperator("AAC123");
		
		Bus bus = new Bus()
				.setBusCode("JAS123")
				.setBusModel("Mercedes")
				.setCapacity(20)
				.setHaltCost(4)
				.setRunCost(20)
				.setAvailable(true)
				.setOperator(busOperator);
		
		busService.addBus(bus);
		
	}

}
