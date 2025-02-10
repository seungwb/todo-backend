package kr.co.tododeungjang.web.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtProvider {

    private SecretKey secretKey = Jwts.SIG.HS256.key().build();

    //JWT 생성 메서드
    public String create(String email){
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); //현재시간에서 1시간 추가

        String jwt = Jwts.builder()
                .signWith(secretKey)        //암호화 알고리즘
                .subject(email)             //사용자 식별자 값
                .issuedAt(new Date())       //생성 시간
                .expiration(expiredDate)    //만료 시간
                .compact();                 //압축하여 객체 생성

        return jwt;
    }
    // expirationTime getter
    public int getExpirationTime(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Date expiration = claims.getExpiration();
        long expirationInSeconds = (expiration.getTime() - System.currentTimeMillis()) / 1000; // 초 단위 변환

        return (int) expirationInSeconds;
    }

    //JWT 검증 메서드
    public String validate(String jwt){
        Claims claims = null;

        try{
            claims = Jwts.parser().verifyWith(secretKey)    //서명 검즘
                    .build()                                //빌드
                    .parseSignedClaims(jwt)                 //JWT 파싱
                    .getPayload();                          //Claims 추출

        } catch (JwtException e){
            e.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }
}
