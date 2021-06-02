package com.letslearn.eventify.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil  implements Serializable{

	private static final long serialVersionUID = -3442219705574219514L;
	
	@Value("${jwt.token-validity}")
	public long JWT_TOKEN_VALIDITY;

	@Value("${jwt.secret}")
	private String jwtSecret;
	
	public String generateToken(UserDetails userDetails) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	
	public boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = extractUserNameFromToken(token);
		
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		String jwt =
		Jwts.builder()
		.setClaims(claims)
		.setSubject(subject)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + hoursToMilliseconds(JWT_TOKEN_VALIDITY)))
		.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return jwt;
	}
	
	public String extractUserNameFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getSubject);
	}

	

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}


	private Claims getAllClaimsFromToken(String token) {
		
		//You should use parseClaimsJws instead of parseClaimsJwt to verify the JWS (JWT with signature) 
			
		return Jwts
			 .parser()
			 .setSigningKey(jwtSecret)
			 .parseClaimsJws(token)
			 .getBody();
	}
	

	private boolean isTokenExpired(String token) {
		
		final Date expiration = getExpirationDateFromToken(token);
		
		return expiration.before(new Date());
	}



	private Date getExpirationDateFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getExpiration);
	}


	private long hoursToMilliseconds(long hours) {
		
		return hours * 60 *60 * 1000;
		
	}

}
