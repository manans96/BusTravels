/**
 * 
 */
package com.manan.busservice.security.jwt;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * @author Manan Sanghvi
 *
 */
@Data
public class JWTUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8210236848063708133L;
	private static final String ROLE_PREFIX = "ROLE_";
	
	private String username;
	private String password;
	private boolean enabled;
	private String role;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private Set<GrantedAuthority> authorities = new HashSet<>();
	
	public JWTUserDetails(String username, String password, String role, boolean enabled) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
