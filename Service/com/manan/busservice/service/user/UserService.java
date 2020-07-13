package com.manan.busservice.service.user;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;

/**
 * @author Manan Sanghvi
 *
 */

public interface UserService {
	
	User signup(User user, UserAuth userAuth);
	
	User login(User user, UserAuth userAuth);
	
	User updateProfile(User user);
	
	User updatePassword(User user, UserAuth userAuth);

}
