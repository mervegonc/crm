package com.project.crm.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	// generate JWT token
	public String generateToken(Authentication authentication) {

		String username = authentication.getName();

		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
// userame encoded
		String token = Jwts.builder().subject(username).issuedAt(new Date()).expiration(expireDate).signWith(key())
				.compact();

		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	// get username from JWT token
	public String getUsername(String token) {

		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	// validate JWT token

	// validate JWT token
	public boolean validateToken(String token) {
		Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
		return true;

	}

	public String getUsernameFromJWT(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

	// validate JWT token

}