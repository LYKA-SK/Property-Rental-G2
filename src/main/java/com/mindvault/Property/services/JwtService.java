package com.mindvault.Property.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.mindvault.Property.entities.Role;
import com.mindvault.Property.entities.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    // Make sure this key is long enough for HS256
    private static final String SECRET_KEY = "YourVerySecretKeyForJwtSigningMustBeVeryLongAndStrong1234567890";

    // -------------------------------
    // Extract username (email) from JWT
    // -------------------------------
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // -------------------------------
    // Check if token is valid for a user
    // -------------------------------
    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getEmail())) && !isTokenExpired(token);
    }

    // -------------------------------
    // Extract any claim from JWT
    // -------------------------------
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // -------------------------------
    // Generate JWT access token with roles
    // -------------------------------
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        // Include roles in the token
        claims.put("roles", user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));
        return generateToken(claims, user.getEmail());
    }

    // -------------------------------
    // Internal helper to generate JWT with claims
    // -------------------------------
    private String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24)) // 1 day
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // -------------------------------
    // Generate refresh token (7 days)
    // -------------------------------
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // 7 days
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // -------------------------------
    // Check if JWT is expired
    // -------------------------------
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // -------------------------------
    // Extract all claims from JWT
    // -------------------------------
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // -------------------------------
    // Convert SECRET_KEY to signing key
    // -------------------------------
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
