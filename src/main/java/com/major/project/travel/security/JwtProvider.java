package com.major.project.travel.security;

import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;


/**
 * Created by ktKhang on 31, Oct, 2018
 **/
@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //    @Value("${app.jwtSecret}")
    private String jwtSecret = "JWTSuperSecretKeyForProjectTravelWebTeamTieuLuanChuyenNganhITK15SPKT20180411ManyThanksForAllYourSupport";

//    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs = 604800000;

    public String generateToken(User authenticationUser) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        String jws = Jwts.builder()
                .setSubject(authenticationUser.getUid())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .signWith(key)
                .claim("role", authenticationUser.getRole().getRoleName())
                .compact();
        System.out.println(jws);
        return jws;
    }

    public String getUserUidFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public void validateToken(String authToken) {
        System.out.println(jwtSecret);
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            System.out.println("Yes");
        } catch (SignatureException ex) {
            throw new RestException("Invalid JWT signature", HttpServletResponse.SC_FORBIDDEN);
        } catch (MalformedJwtException ex) {
            throw new RestException("Invalid JWT token", HttpServletResponse.SC_FORBIDDEN);
        } catch (ExpiredJwtException ex) {
            throw new RestException("Expired JWT token", HttpServletResponse.SC_FORBIDDEN);
        } catch (UnsupportedJwtException ex) {
            throw new RestException("Unsupported JWT token", HttpServletResponse.SC_FORBIDDEN);
        } catch (IllegalArgumentException ex) {
            throw new RestException("JWT claims string is empty.", HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
