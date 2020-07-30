/**
 * 
 */
package com.manan.busservice.security;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.utility.DateUtils;
import com.manan.busservice.utility.mnemonics.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author Manan Sanghvi
 *
 */
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
		UserRole role = user.getRole();
		String subject = user.getUserName();
		return generateToken(role, subject);
	}
	
	private String generateToken(UserRole role, String subject) {
		
		return Jwts.builder()
				.setSubject(role.getRoleString())
				.setAudience(subject)
				.setIssuedAt(DateUtils.today())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY))
				.signWith(key)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	// T means any object ie it signifies Object
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
}
