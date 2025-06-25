package com.example.auth_service.jwt;

import com.example.auth_service.dto.JwtClaims;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey getSigninKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email, List<? extends GrantedAuthority> authorities) {
        List<String> nameAuthorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .subject(email)
                .claim("authorities", nameAuthorities)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigninKey())
                .compact();
    }

    public JwtClaims parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            List<?> rawList = claims.get("authorities", List.class);

            List<String> authorities = rawList.stream()
                    .map(Objects::toString)
                    .collect(Collectors.toList());

            return new JwtClaims(claims.getSubject(), authorities);

        } catch (JwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            throw new RuntimeException("Invalid JWT", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigninKey())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT token expired: {}", e.getMessage());
            return false;
        } catch (JwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }
}
