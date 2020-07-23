package com.manan.busservice.service.user;

import java.util.List;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;

/**
 * @author Manan Sanghvi
 *
 */

public interface UserService {
	
	User signup(User user, UserAuth userAuth);
	
	User login(String userName, String password);
	
	User updateProfile(User user);
	
	User updatePassword(User user, UserAuth userAuth, UserAuth newUserAuth);
	
	User changeRole(User user);
	
	User findUser(String userName);

	User findUser(User user);
	
	List<User> findAllUsers();

}
