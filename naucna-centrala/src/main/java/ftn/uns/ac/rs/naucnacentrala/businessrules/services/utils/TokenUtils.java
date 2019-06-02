package ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration}")
    private Long expiration;

    @Value("${token.header}")
    private String header;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret.getBytes("UTF-8")).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    public String generateToken(String userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails);
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8")).compact();
        } catch (UnsupportedEncodingException ex) {
            return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret).compact();
        }
    }

//    public String generateTokenForTransaction(String sellerUUID) {
//        Claims claims = Jwts.claims().setSubject(sellerUUID + Constants.TRANSACTION);
//        claims.put(Constants.AUTH, Constants.MICROSERVICES);
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + Constants.EXPIRATION);
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, Constants.TRANSSECRKEY)
//                .compact();
//
//        return token;
//    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Bearer");
        if (bearerToken != null) {
            return bearerToken;
        }
        return null;
    }

    public boolean validateToken(String token) throws JwtException, IllegalArgumentException {
        try {
            Jwts.parser().setSigningKey(this.secret.getBytes("UTF-8")).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Object getRoleList(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).
                getBody().get("ROLE");
    }


}
