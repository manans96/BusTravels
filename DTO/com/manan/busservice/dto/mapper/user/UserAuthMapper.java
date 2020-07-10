package com.manan.busservice.dto.mapper.user;

import com.manan.busservice.dto.model.user.UserAuth;
import com.manan.busservice.model.user.UserAuthEntity;

public class UserAuthMapper {
	
	public static UserAuth toUserAuth(UserAuthEntity userAuth) {
		
		return new UserAuth()
				.setPassword(userAuth.getPassword())
				.setLastUpdate(userAuth.getLastUpdate());
		
	}

}
