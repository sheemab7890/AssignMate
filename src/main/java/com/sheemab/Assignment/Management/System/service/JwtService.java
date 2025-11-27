package com.sheemab.Assignment.Management.System.service;



import io.jsonwebtoken.*;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;   // Base64 encoded secret

    @Value("${security.jwt.expiration}")
    private long jwtExpirationMs;   // in milliseconds

    /** Generate JWT token */
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)   // = setSubject()
                .issuedAt(new Date()) // = setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignKey()) // algorithm auto-detected
                .compact();
    }

    /** Extract username from token */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /** Check if token is expired */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }

    /** Validate token */
    public boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    /** Generic claim extractor */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())   // verifies signature
                .build()
                .parseSignedClaims(token) // parses JWT
                .getPayload();             // extracts body (claims)

        return claimsResolver.apply(claims);
    }

    /** Create signing key from Base64 secret */
//    private SecretKey getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }



}


