/**
 * 
 */
package com.manan.busservice.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.user.UserEntity;
import com.manan.busservice.response.EntityResponse;

/**
 * @author Manan Sanghvi
 *
 */
public class JWTUserDetailsService implements UserDetailsService {

	private Repositories.Container repos;

	@Autowired
	public JWTUserDetailsService(Repositories.Container repos) {
		this.repos = repos;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String userName = null;
		String password = null;
		Optional<UserEntity> optional = repos.userRepository.findByUserName(username);
		if(optional.isPresent()) {
			UserEntity user = optional.get();
			userName = user.getUserName();
			password = user.getUserAuth().getPassword();
		} else {
			throw new BusAppException.EntityNotFoundException(EntityResponse.USER);
		}
		
		//this User is from
		//org.springframework.security.core.userdetails.User.User(String username, String password, Collection<? extends GrantedAuthority> authorities)
		return new User(userName, password, Collections.emptyList());
	}

}
