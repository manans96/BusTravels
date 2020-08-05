/**
 * 
 */
package com.manan.busservice.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**	
 * @author Manan Sanghvi
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JWTRequestFilter jwtFilter;
	private JWTUserDetailsService jwtUserDetailsService;
	
	@Autowired
	public WebSecurityConfig(DataSource dataSource, JWTRequestFilter jwtFilter, JWTUserDetailsService jwtUserDetailsService) {
		this.dataSource = dataSource;
		this.jwtFilter = jwtFilter;
		this.jwtUserDetailsService = jwtUserDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.userDetailsService(jwtUserDetailsService)
			.passwordEncoder(passwordEncoder());
//				.jdbcAuthentication()
//				.dataSource(dataSource)
//				.passwordEncoder(passwordEncoder())
//				.usersByUsernameQuery(
//						"select u.user_name as username, a.password as password, u.enabled as enabled from user u, userauth a"
//						+ " where id_user=id_user_auth=1 and user_name = ?")
//				.authoritiesByUsernameQuery("select user_name as username, role as authority from user where user_name = ?");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			//authorize these requests for all
//			.authorizeRequests().antMatchers("/api/v1/login", "/api/v1/signup").permitAll()
//			.antMatchers("/api/v1/admin")
//				.hasAnyRole(UserRole.ADMIN.getRoleString(), UserRole.SUPERADMIN.getRoleString())
//			.antMatchers("/api/v1/user")
//				.hasAnyRole(UserRole.ADMIN.getRoleString(), UserRole.USER.getRoleString(), UserRole.OPERATOR.getRoleString(), UserRole.SUPERADMIN.getRoleString())
//			.antMatchers("/api/**").authenticated()
			.authorizeRequests().antMatchers("/api/v1/**").permitAll()
			.anyRequest().authenticated();
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
