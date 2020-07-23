package com.manan.busservice.service.user;

import java.util.List;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;

/**
 * @author Manan Sanghvi
 *
 */

@Deprecated
public interface UserService {
	
	User signup(User user, UserAuth userAuth);
	
	User login(String userName, String password);
	
	User updateProfile(User user);
	
	User updatePassword(String userName, String oldPassword, String newPassword);
	
	User changeRole(String userName, String role);
	
	User findUser(String userName);

	@Deprecated
	User findUser(User user);
	
	List<User> findAllUsers();

}
