package com.backend.vinbook.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Thay secret bằng biến môi trường production; đây chỉ ví dụ dev
    private static final String SECRET = "6980396a36a308bfc93d146548bddc4ba36e51e0302fe8328a1df49e8ac46ae6";
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60L; // seconds

    private final Key hmacKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        CustomerUserDetails user =  (CustomerUserDetails) userDetails;
        claims.put("username",user.getUsername());
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("name", user.getName());
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JWT_TOKEN_VALIDITY * 1000))
                .signWith(hmacKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(hmacKey) // Sử dụng lại hmacKey
                .build().parseClaimsJws(token).getBody().getSubject();
    }

}
