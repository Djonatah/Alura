package com.alura.djonatah.medvollapi.infrastrcture.security;

import com.alura.djonatah.medvollapi.domain.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TokenService {

    @Value("${medvollapi.security.token.secret}")
    String secret;
    String issuer = "medvollapi";

    public String generateToken(User user){
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("id ", user.getId())
                    .withSubject(user.getUsername())
                    .withExpiresAt(getTokenExpirationDate())
                    .sign(algorithm);
        } catch(Exception e){
            throw new RuntimeException("error generating jwt token", e);
        }
    }

    public String getSubject(String jwtToken){
        DecodedJWT decodedJWT;
        try {
            var algorithm = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            decodedJWT = verifier.verify(jwtToken);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid or expired token");
        }
    }

    private Instant getTokenExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));
    }
}
