package com.manan.busservice.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.manan.busservice.dto.mapper.user.UserMapper;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;
import com.manan.busservice.jpa.repository.UserRepository;
import com.manan.busservice.model.user.UserAuthEntity;
import com.manan.busservice.model.user.UserEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */

public class UserServiceImpl implements UserService {

	//These are the autowired fields autowired by constructor
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//These are the normal fields needed by Business logic
	
	private Optional<UserEntity> optional;
	
	@Override
	public User signup(User user, UserAuth userAuth) {

		findByUserName(user.getUserName());
		if(!optional.isEmpty()) {
			return login(user, userAuth);
		} else {
			return UserMapper.toUser(userRepository.save(new UserEntity()
					.setUserName(user.getUserName())
					.setFirstName(user.getFirstName())
					.setLastName(user.getLastName())
					.setEmail(user.getEmail())
					.setPhoneNo(user.getPhoneNo())
					.setRole("user")
					.setUserAuth(new UserAuthEntity()
							.setPassword(userAuth.getPassword())
							.setLastUpdate(DateUtils.today()))));
		}
	}

	@Override
	public User login(User user, UserAuth userAuth) {

		findByUserName(user.getUserName());
		if(!optional.isEmpty()) {
			return UserMapper.toUser(optional.get());
		} else {
			return new User();
		}
	}

	@Override
	public User updateProfile(User user) {

		findByUserName(user.getUserName());
		if(!optional.isEmpty()) {
			UserEntity userEntity = optional.get();
			return UserMapper.toUser(userRepository.save(userEntity
					.setEmail(user.getEmail())
					.setFirstName(user.getFirstName())
					.setLastName(user.getLastName())
					.setPhoneNo(user.getPhoneNo())));
		} else {
			return new User();
		}
	}

	@Override
	public User updatePassword(User user, UserAuth userAuth) {

		findByUserName(user.getUserName());
		if(!optional.isEmpty()) {
			UserEntity userEntity = optional.get();
			UserAuthEntity userAuthEntity = userEntity.getUserAuth();
			return UserMapper.toUser(userRepository.save(userEntity
					.setUserAuth(userAuthEntity
							.setPassword(userAuth.getPassword())
							.setLastUpdate(DateUtils.today()))));
		} else {
			return new User();
		}
	}

	private void findByUserName(String userName) {
		optional = userRepository.findByUserName(userName);
	}

}
