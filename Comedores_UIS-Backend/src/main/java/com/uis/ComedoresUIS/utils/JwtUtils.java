package com.uis.ComedoresUIS.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    @Value("${spring.jwt.key.private}")
    private String privateKey;

    @Value("${spring.jwt.user.generator}")
    private String userGenerator;

    //Method for create token
    public String createToken(Authentication authentication) {
        //First, Choose the algorithm for encryption, for example HMAC256, HS256...
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        //Get your code and role
        String codeAdmin = authentication.getPrincipal().toString();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        //Create token
        String token = JWT.create()
                .withIssuer(userGenerator)
                .withSubject(codeAdmin)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return token;
    }

    //Method for validate token
    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            return verifier.verify(token);

        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token invalid, no Authorized");
        }
    }

    //Method for get code admin
    public String extractCodeAdmin(DecodedJWT token) {
        return token.getSubject();
    }

    //Method for get Claim
    public Claim getSpecificClaim(DecodedJWT token, String claimName) {
        return token.getClaim(claimName);
    }

}
