/**
 * 
 */
package com.manan.busservice.controller.v1.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.controller.v1.controller.user.request.ChangePasswordRequest;
import com.manan.busservice.controller.v1.controller.user.request.UserEditRequest;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.service.Services;

/**
 * @author Manan Sanghvi
 *
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private Services.Container services;
	
	@Autowired
	public UserController(Services.Container services) {
		this.services = services;
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		
		return new ResponseEntity<>("Logout successful", HttpStatus.OK);
	}
	
	@PatchMapping("/edituser/{username}")
	public ResponseEntity<String> editUser(@PathVariable String username, @RequestBody UserEditRequest user) {
		
		services.userService.updateProfile(new User()
				.setUserName(username)
				.setEmail(user.getEmail())
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setPhoneNo(user.getPhoneNo())
				);
		
		return new ResponseEntity<>("User " + username + " edited successfully", HttpStatus.OK);
	}
	
	@PatchMapping("/changepassword/{username}")
	public ResponseEntity<String> editUser(@PathVariable String username, @RequestBody ChangePasswordRequest password) {
		
		services.userService.updatePassword(username, password.getOldPassword(), password.getNewPassword());
		
		return new ResponseEntity<>("User " + username + " edited successfully", HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		
		return new ResponseEntity<>(services.userService.findUser(username), HttpStatus.OK);
	}
	
	
}
