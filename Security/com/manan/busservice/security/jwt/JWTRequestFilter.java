/**
 * 
 */
package com.manan.busservice.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.service.Services;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	
	private JWTUtil jwtUtil;
	private Services.Container services;
	private UserDetailsService userDetailsService;
	
	@Autowired
	public JWTRequestFilter(JWTUtil jwtUtil, Services.Container services, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.services = services;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = null;
		String username = null;
		
		try {
			token = resolveToken(request);
			username = jwtUtil.getUsernameFromToken(token);
		} catch(IllegalArgumentException iae) {
			System.out.println("Unable to get JWT token");
		} catch(ExpiredJwtException eje) {
			System.out.println("The token has been expired");
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			User user = services.userService.findUser(username);
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(token, user)) {
				
				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
	
//	private void resetAuthenticationAfterRequest() {
//		SecurityContextHolder.getContext().setAuthentication(null);
//	}
	
	private String resolveToken(HttpServletRequest request) {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			return requestTokenHeader.substring(7, requestTokenHeader.length());
		} else {
			logger.warn("JWT token does not begin with Bearer string");
			return null;
		}
	}

}
