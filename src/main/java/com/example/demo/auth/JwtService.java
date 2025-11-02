package com.example.demo.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    private final Key key;
    private final long expirationMinutes;

    public  JwtService(
            @Value("${jwt.secret}") String base64Secret,
            @Value("${jwt.expiration_minutes}") long expirationMinutes
    ){
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));
        this.expirationMinutes = expirationMinutes;
    }

    public String generateToken(String email, Map<String, Object> claims){
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expirationMinutes * 60);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String email){
        return generateToken(email, Map.of());
    }


//    TODO: REVIEW THIS LOGIC LATER
    public boolean isTokenValid(String token, String email){
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token){
        Date exp = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return exp.before(new Date());
    }
}
