package ftn.uns.ac.rs.naucnacentrala.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    @Autowired
    JwtConfig jwtConfig;

    public String generate(JwtUser jwtUser) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtConfig.EXPIRATION);

        Claims claims = Jwts.claims().setSubject(jwtUser.getUser());
        claims.put(jwtConfig.AUTH, jwtConfig.USER);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.TRANSSECRKEY)
                .compact();

        return token;
    }
}