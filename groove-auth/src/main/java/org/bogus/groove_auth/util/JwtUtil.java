package org.bogus.groove_auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final AppProperty appProperty;
    private final AuthProperty authProperty;
    private final Algorithm signing;
    private final String userIdKey;

    JwtUtil(AppProperty appProperty, AuthProperty authProperty) {
        this.appProperty = appProperty;
        this.authProperty = authProperty;
        this.signing = Algorithm.HMAC256(authProperty.getAuthKey());
        this.userIdKey = "userId";
    }

    public DecodedJWT verify(String token) {
        return JWT.require(signing)
            .build()
            .verify(token);
    }

    public Long getUserIdByToken(String token) {
        DecodedJWT decodedJwt = JWT.decode(token);
        return decodedJwt.getClaim(userIdKey).asLong();
    }

    public LocalDateTime getExpiryByToken(String token) {
        DecodedJWT decodedJwt = JWT.decode(token);
        return LocalDateTime.ofInstant(decodedJwt.getExpiresAt().toInstant(), ZoneId.systemDefault());
    }

    public String generateAccessToken(Long userId) {
        long currentTimeMillis = System.currentTimeMillis();

        return JWT.create()
            .withIssuer(appProperty.getName())
            .withIssuedAt(new Date(currentTimeMillis))
            .withSubject("access")
            .withClaim(userIdKey, userId)
            .withExpiresAt(new Date(currentTimeMillis + authProperty.getAccessExpiration()))
            .sign(signing);
    }

    public String generateRefreshToken(Long userId) {
        long currentTimeMillis = System.currentTimeMillis();

        return JWT.create()
            .withIssuer(appProperty.getName())
            .withIssuedAt(new Date(currentTimeMillis))
            .withSubject("refresh")
            .withClaim(userIdKey, userId)
            .withExpiresAt(new Date(currentTimeMillis + authProperty.getRefreshExpiration()))
            .sign(signing);
    }
}
