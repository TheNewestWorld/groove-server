package org.bogus.groove.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.bogus.groove.domain.user.token.UserToken;
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

    public UserToken generateAccessToken(Long userId) {
        LocalDateTime expiresAt = LocalDateTime.now().plus(authProperty.getAccessExpiration());
        return generateToken(userId, expiresAt, "access");
    }

    public UserToken generateRefreshToken(Long userId) {
        LocalDateTime expiresAt = LocalDateTime.now().plus(authProperty.getRefreshExpiration());
        return generateToken(userId, expiresAt, "refresh");
    }

    public UserToken generateToken(Long userId, LocalDateTime expiresAt, String subject) {
        String token = JWT.create()
            .withIssuer(appProperty.getName())
            .withIssuedAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
            .withSubject(subject)
            .withClaim(userIdKey, userId)
            .withExpiresAt(expiresAt.atZone(ZoneId.systemDefault()).toInstant())
            .sign(signing);

        return new UserToken(token, LocalDateTime.from(expiresAt));
    }
}
