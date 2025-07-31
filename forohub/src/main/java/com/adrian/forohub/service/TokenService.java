package com.adrian.forohub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMinutes;

    public String generarToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant ahora = Instant.now();
        Instant expiracion = ahora.plusSeconds(expirationMinutes * 60);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(ahora))
                .withExpiresAt(Date.from(expiracion))
                .sign(algorithm);
    }

    public String validarToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
