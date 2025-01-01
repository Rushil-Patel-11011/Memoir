package com.rushil.Memoir.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtUtil {
    private String SECRET_KEY = "TaK+HaV^uvCHEFsEVfypW#7g9^k*Z8$V";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()//Returns a new JwtParserBuilder instance that can be configured to create an immutable/thread-safe JwtParser.
                .verifyWith(getSigningKey())//Sets the signature verification PublicKey used to verify all encountered JWS signatures. If the encountered JWT string is not a JWS
                .build()
                .parseSignedClaims(token)//returns the parsed cryptographically-verified Claims JWS.
                .getPayload();//Returns the JWT payload, either a byte[] or a Claims instance.
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims) //key:value pairs in jwt payload are known as claims
                .subject(subject) //"sub" in jwt payload
                .header().empty().add("typ","JWT")// it clears out the default header and adds a new header
                .and()
                .issuedAt(new Date(System.currentTimeMillis())) //"iss" in jwt payload
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 60 minutes expiration time
                .signWith(getSigningKey()) //it signs the jwt with the key
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
