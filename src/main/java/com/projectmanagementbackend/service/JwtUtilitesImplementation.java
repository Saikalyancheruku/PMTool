package com.projectmanagementbackend.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtUtilitesImplementation implements JwtUtilites{
	// private String SECRET_KEY = "2@hso@ekowhwbd";
	 @Value("${jwt.secret}")
	    private String secretKey;
	@Override
	  public String generateToken(String emailAddress) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims,emailAddress);
	    }

	    @Override
		public String createToken(Map<String, Object> claims, String subject) {
	    	 byte[] keyBytes = Base64.getDecoder().decode(secretKey);
	         SecretKey key = Keys.hmacShaKeyFor(keyBytes);
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(subject)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
	                .signWith(key,SignatureAlgorithm.HS256)
	                .compact();
	    }
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    @Override
	    public String extractEmail(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	   

	    private Claims extractAllClaims(String token) {
	        byte[] keyBytes = Base64.getDecoder().decode(secretKey); // Corrected: Use the same key for parsing
	        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
	        return Jwts.parserBuilder()
	                   .setSigningKey(key) // Use decoded secret key here
	                   .build()
	                   .parseClaimsJws(token)
	                   .getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	    @Override
	    public Boolean validateToken(String token, String emailAddress) {
	        final String username = extractEmail(token);
	        return (username.equals(emailAddress) && !isTokenExpired(token));
	    }
}
