package com.manan.busservice.dto.mapper.user;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.model.user.UserEntity;

public class UserMapper {
	
	public static User toUser(UserEntity user) {
		
		return new User()
				.setUserName(user.getUserName())
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setEmail(user.getEmail())
				.setPhoneNo(user.getPhoneNo())
				.setRole(user.getRole());
		
	}
	
	public static List<User> toUser(List<UserEntity> user) {
		
		List<User> users = new ArrayList<>();
		for(UserEntity u : user) {
			users.add(toUser(u));
		}
		return users;
	}

}
