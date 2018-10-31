package com.major.project.travel.security;

import com.major.project.travel.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


/**
 * Created by ktKhang on 31, Oct, 2018
 **/
@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //    @Value("${app.jwtSecret}")
    private String jwtSecret = "JWTSuperSecretKey";
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs = 604800000;

    public String generateToken(User authenticationUser) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        String jws = Jwts.builder()
                .setSubject(authenticationUser.getUid())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .signWith(key)
                .claim("role", authenticationUser.getRole().getRoleName())
                .compact();
        System.out.println(jws);
        return jws;
    }

}
