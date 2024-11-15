package com.JWT.JWT.Authnetication.Security;

import com.JWT.JWT.Authnetication.Utils.PassValue;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtHelper {

    Logger logger = LoggerFactory.getLogger(JwtHelper.class);


    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(PassValue.JWT_KEY).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.error("Failed to parse token: {}", e.getMessage());
            throw new RuntimeException("Invalid token");
        }
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userDetails.getUsername()); // or however you fetch the email
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + PassValue.JWT_TOKEN_VALID))
                .signWith(SignatureAlgorithm.HS256, PassValue.JWT_KEY)
                .compact();
    }
}
