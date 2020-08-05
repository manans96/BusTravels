/**
 * 
 */
package com.manan.busservice.security.jwt;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.user.UserEntity;

/**
 * @author Manan Sanghvi
 *
 */
@Service
public class JWTUserDetailsService implements UserDetailsService {

	private Repositories.Container repos;

	@Autowired
	public JWTUserDetailsService(Repositories.Container repos) {
		this.repos = repos;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String password = null;
		String role;
		
		Optional<UserEntity> optional = repos.userRepository.findByUserName(username);
		if(optional.isPresent()) {
			UserEntity user = optional.get();
			password = user.getUserAuth().getPassword();
			role = user.getRole();
		} else {
			throw new UsernameNotFoundException("User with username: " + username + " is not found");
		}
		return new JWTUserDetails(username, password, role, true);
	}

}
