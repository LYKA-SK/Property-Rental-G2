package com.mindvault.Property.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mindvault.Property.entities.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    // Ensure this is a Base64 encoded string if using Decoders.BASE64.decode
    private static final String SECRET_KEY = "YourVerySecretKeyForJwtSigningMustBeVeryLongAndStrong1234567890YourVerySecretKeyForJwtSigningMustBeVeryLongAndStrong1234567890";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // UPDATED: Accepts UserDetails to prevent ClassCastException in Filter
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Used during Login/Registration
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        // Important: Prepend ROLE_ here too if you want roles inside the JWT payload
        claims.put("roles", user.getRoles().stream()
                .map(role -> "ROLE_" + role.getName().toUpperCase())
                .collect(Collectors.toList()));
        
        return generateToken(claims, user.getEmail());
    }

    private String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24)) // 1 day
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // 7 days
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        // If your key is plain text, use Keys.hmacShaKeyFor(SECRET_KEY.getBytes())
        // If your key is Base64, keep this:
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}