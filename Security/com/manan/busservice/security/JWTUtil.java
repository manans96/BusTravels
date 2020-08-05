/**
 * 
 */
package com.manan.busservice.security;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.utility.DateUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class JWTUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4742132805581811191L;
	
	private static final long JWT_VALIDITY = 4 * 60 * 60 * 1000;
	
//	@Value("${jwt.secret}")
//	private String secret;
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	
	public String userToken(User user) {
		String subject = user.getUserName();
		return generateToken(subject);
	}
	
	private String generateToken(String subject) {
		
		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(DateUtils.today())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY))
				.signWith(key)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	// T means any object ie it signifies Object
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
	
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.after(DateUtils.today());
	}
	
	public boolean validateToken(String token, User user) {
		String username = getUsernameFromToken(token);
		return username.equals(user.getUserName()) && isTokenExpired(token);
	}
}
