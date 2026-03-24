package kr.co.tododeungjang.web.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String create(String email, String role) {
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        return Jwts.builder()
                .signWith(secretKey())
                .subject(email)
                .issuedAt(new Date())
                .expiration(expiredDate)
                .claim("role", role)
                .compact();
    }

    public int getExpirationTime(String token) {
        Claims claims = parseClaims(token);
        if (claims == null) return 0;
        long expirationInSeconds = (claims.getExpiration().getTime() - System.currentTimeMillis()) / 1000;
        return (int) expirationInSeconds;
    }

    public String validateEmail(String jwt) {
        Claims claims = parseClaims(jwt);
        return claims != null ? claims.getSubject() : null;
    }

    public String validateRole(String jwt) {
        Claims claims = parseClaims(jwt);
        return claims != null ? (String) claims.get("role") : null;
    }

    private Claims parseClaims(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (JwtException e) {
            e.printStackTrace();
            return null;
        }
    }
}
