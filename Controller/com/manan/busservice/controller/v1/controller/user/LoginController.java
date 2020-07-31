/**
 * 
 */
package com.manan.busservice.controller.v1.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.controller.v1.controller.user.request.LoginRequest;
import com.manan.busservice.controller.v1.controller.user.request.SignUpRequest;
import com.manan.busservice.controller.v1.controller.user.response.JWTResponse;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;
import com.manan.busservice.security.JWTUtil;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.mnemonics.UserRole;

/**
 * @author Manan Sanghvi
 *
 */
@RestController
@RequestMapping("/api/v1")
public class LoginController {
	
	private Services.Container services;
	private JWTUtil jwtUtil;
	
	@Autowired
	public LoginController(Services.Container services, JWTUtil jwtUtil) {
		this.services = services;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignUpRequest signup) {
		
		services.userService.signup(
			new User()
				.setEmail(signup.getEmail())
				.setUserName(signup.getUserName())
				.setFirstName(signup.getFirstName())
				.setLastName(signup.getLastName())
				.setPhoneNo(signup.getPhoneNo())
				.setRole(UserRole.USER),
			new UserAuth()
				.setPassword(signup.getPassword()));
		services.userService.login(signup.getUserName(), signup.getPassword());

		return new ResponseEntity<>("Signup for " + signup.getUserName() + " successful", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JWTResponse> login(@RequestBody LoginRequest login) {
		
		services.userService.login(login.getUserName(), login.getPassword());
		User user = services.userService.findUser(login.getUserName());
		
		final String token = jwtUtil.userToken(user);
			
		return new ResponseEntity<>(new JWTResponse(token, "User " + login.getUserName() + " logged in succesfully"),
				HttpStatus.OK);
	}

}
