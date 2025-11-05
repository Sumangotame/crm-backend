package com.suman.crm.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final Key key;
    private final long expirationMs;

    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration-ms}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(String subject, Map<String, Object> extraClaims) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);
        return Jwts.builder().setClaims(extraClaims).setSubject(subject).setIssuedAt(now).setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> fn) {
        Claims claims = parseClaims(token);
        return fn.apply(claims);
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw e;
        }
    }

    public boolean isTokenValid(String token, String username) {
        try {
            final String tokenusername = extractUsername(token);
            return (tokenusername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }

    }

    private boolean isTokenExpired(String token) {
        final Date exp = extractClaim(token, Claims::getExpiration);
        return exp.before(new Date());
    }

}
