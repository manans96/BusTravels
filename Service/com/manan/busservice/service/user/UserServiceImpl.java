package com.manan.busservice.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manan.busservice.dto.mapper.user.UserMapper;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.user.UserAuthEntity;
import com.manan.busservice.model.user.UserEntity;
import com.manan.busservice.response.ResponseEntity;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */

@Service
public class UserServiceImpl implements Services.UserService {

	//These are the autowired fields autowired by constructor
	private Repositories.Container repos;

	@Autowired
	public UserServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	//These are the normal fields needed by Business logic
	
	private Optional<UserEntity> optional;
	
	
	private void findByUserName(String userName) {
		optional = repos.userRepository.findByUserName(userName);
	}
	
	@Override
	public User signup(User user, UserAuth userAuth) {

		findByUserName(user.getUserName());
		if(optional.isEmpty()) {
			try {
				return UserMapper.toUser(repos.userRepository.save(new UserEntity()
						.setUserName(user.getUserName())
						.setFirstName(user.getFirstName())
						.setLastName(user.getLastName())
						.setEmail(user.getEmail())
						.setPhoneNo(user.getPhoneNo())
						.setRole(user.getRole())
						.setUserAuth(new UserAuthEntity()
								.setPassword(userAuth.getPassword())
								.setLastUpdate(DateUtils.today()))));
			} catch (RuntimeException re) {
				throw new BusAppException.ValidationException(ResponseEntity.USER);
			}
		}
		throw new BusAppException.DuplicateEntityException(ResponseEntity.USER);
	}

	@Override
	public User login(String userName, String password) {

		findByUserName(userName);
		if(optional.isPresent()) {
			boolean passAuth = password.equals(optional.get().getUserAuth().getPassword());
			if(passAuth) {
				return UserMapper.toUser(optional.get());
			}
			if(!passAuth) {
				throw new BusAppException.WrongCredentialsException(ResponseEntity.USER);
			}
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.USER);
	}

	@Override
	public User updateProfile(User user) {

		findByUserName(user.getUserName());
		if(optional.isPresent()) {
			try {
				UserEntity userEntity = optional.get();
				return UserMapper.toUser(repos.userRepository.save(userEntity
						.setEmail(user.getEmail())
						.setFirstName(user.getFirstName())
						.setLastName(user.getLastName())
						.setPhoneNo(user.getPhoneNo())));
			} catch(RuntimeException re) {
				throw new BusAppException.ValidationException(ResponseEntity.USER);
			}
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.USER);
	}

	@Override
	public User updatePassword(String userName, String oldPassword, String newPassword) {

		findByUserName(userName);
		if(optional.isPresent()) {
			boolean passMatch = oldPassword.equals(optional.get().getUserAuth().getPassword());
			if(passMatch) {
				UserEntity userEntity = optional.get();
				UserAuthEntity userAuthEntity = userEntity.getUserAuth();
				return UserMapper.toUser(repos.userRepository.save(userEntity
						.setUserAuth(userAuthEntity
								.setPassword(newPassword)
								.setLastUpdate(DateUtils.today()))));
			}
			if(!passMatch) {
				throw new BusAppException.WrongCredentialsException(ResponseEntity.USER);
			}
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.USER);
	}

	@Override
	public User changeRole(String userName, String role) {

		findByUserName(userName);
		if(optional.isPresent()) {
			UserEntity userEntity = optional.get();
			return UserMapper.toUser(repos.userRepository.save(userEntity
					.setRole(role)));
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.USER);
	}

	@Override
	public User findUser(String userName) {

		findByUserName(userName);
		if(optional.isPresent()) {
			return UserMapper.toUser(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.USER);
	}

	@Override
	public List<User> findAllUsers() {
		return UserMapper.toUser(repos.userRepository.findAll());
	}

	@Override
	@Deprecated
	public User findUser(User user) {

		findByUserName(user.getUserName());
		return UserMapper.toUser(optional.get());
	}

}
