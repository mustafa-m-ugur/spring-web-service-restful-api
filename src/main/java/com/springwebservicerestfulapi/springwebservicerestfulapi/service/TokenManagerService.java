package com.springwebservicerestfulapi.springwebservicerestfulapi.service;

import com.springwebservicerestfulapi.springwebservicerestfulapi.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManagerService {
    private static final int validityDuration = 5 * 60 * 1000;
    private UserService userService;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Setter
    private String loginToken;

    public TokenManagerService(UserService userService) {
        this.userService = userService;
    }

    public TokenDto generateToken(String email) {
        String token = io.jsonwebtoken.Jwts.builder()
                .setSubject(email)
                .setIssuer("localhost")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityDuration))
                .signWith(key)
                .compact();

        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess_token(token);
        return tokenDto;
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public boolean tokenValidate(String token) {
        if (getEmailToken(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    public String getEmailToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Object getUser() {
        return userService.getUserDtoByEmail(getEmailToken(getLoginToken()));
    }

    public String getLoginToken() {
        return loginToken;
    }

}
