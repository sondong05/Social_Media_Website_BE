            package com.backend.vinbook.jwt;

            import com.backend.vinbook.entity.Role;
            import io.jsonwebtoken.Claims;
            import io.jsonwebtoken.Jwts;
            import io.jsonwebtoken.SignatureAlgorithm;
            import org.springframework.security.core.userdetails.UserDetails;
            import org.springframework.stereotype.Component;

            import java.util.Date;
            import java.util.HashMap;
            import java.util.Map;

            @Component
            public class JwtUtil {
                private static  final Long JWT_TOKEN_VALIDITY = 5*60*60L;

                private static final String SECRET =
                        "6980396a36a308bfc93d146548bddc4ba36e51e0302fe8328a1df49e8ac46ae670d0cb933e2f939ef16c674088b1bd64e07ae0c866877528a841521df93b31f2";
                public String generateToken(UserDetails userDetails) {
                    Map<String, Object> claims = new HashMap<>();
                    CustomerUserDetails user =  (CustomerUserDetails) userDetails;
                    String role = user.getRole().name();  // chuyển enum thành string, ví dụ "ADMIN"
                    if (!role.startsWith("ROLE_")) {
                        role = "ROLE_" + role;
                    }
                    claims.put("id", user.getId());
                    claims.put("email", user.getEmail());
                    claims.put("role", role);
                    claims.put("name", user.getName());
                    claims.put("profileId", user.getProfileId());
                    return doGenerateToken(claims, user.getUsername());
                }

                public String doGenerateToken(Map<String, Object> claims, String subject) {
                    return Jwts.builder()
                            .setClaims(claims)
                            .setSubject(subject)
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                            .signWith(SignatureAlgorithm.HS512, SECRET)
                            .compact();
                }

                public String extractUsername(String token) {
                    return extractAllClaims(token).getSubject();
                }

                private Claims extractAllClaims(String token) {
                    return Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token)
                            .getBody();
                }

                public boolean isTokenExpired(String token) {
                    return extractAllClaims(token).getExpiration().before(new Date());
                }

                public boolean validateToken(String token, UserDetails userDetails) {
                    final String username = extractUsername(token);
                    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
                }

            }


