/**
 * 
 */
package com.manan.busservice.controller.v1.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.mnemonics.UserRole;

/**
 * @author Manan Sanghvi
 *
 */
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('admin')")
public class UserAdminController {
	
	private Services.Container services;
	
	@Autowired
	public UserAdminController(Services.Container services) {
		this.services = services;
	}
	
	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getAllUsers() {
		
		return new ResponseEntity<>(services.userService.findAllUsers(), HttpStatus.OK);
	}
	
	@PatchMapping("/makeadmin/{username}")
	public ResponseEntity<String> makeAdmin(@PathVariable String username) {
		
		services.userService.changeRole(username, UserRole.ADMIN);
		return new ResponseEntity<>("The user " + username + " is now an admin", HttpStatus.OK);
	}
	
	@PatchMapping("/makeuser/{username}")
	public ResponseEntity<String> makeUser(@PathVariable String username) {
		
		services.userService.changeRole(username, UserRole.USER);
		return new ResponseEntity<>("The admin " + username + " is now a user", HttpStatus.OK);
	}
}
