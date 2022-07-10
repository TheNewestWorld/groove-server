package org.bogus.groove_auth.domain.user.token;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.error.AppException;
import org.bogus.groove_auth.error.ErrorType;
import org.bogus.groove_auth.util.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator {
    private final JwtUtil jwtUtil;
    private final UserTokenReader userTokenReader;
    private final UserTokenUpdater userTokenUpdater;
    private final RedisTemplate<String, Object> redisTemplate;

    public void validateRefreshable(Long userId, String token) {
        validate(token);
        UserToken refreshToken = userTokenReader.readUserTokenByUserId(userId);
        if (!token.equals(refreshToken.getToken())) {
            throw new AppException(ErrorType.UNAUTHORIZED_INVALID_TOKEN);
        }
        if (Duration.between(LocalDateTime.now(), refreshToken.getExpiresAt()).isNegative()) {
            throw new AppException(ErrorType.UNAUTHORIZED_TOKEN_EXPIRED);
        }
    }

    public void validate(String token) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
            throw new AppException(ErrorType.UNAUTHORIZED_TOKEN_EXPIRED);
        }
        try {
            jwtUtil.verify(token);
        } catch (JWTVerificationException e) {
            if (e instanceof TokenExpiredException) {
                throw new AppException(ErrorType.UNAUTHORIZED_TOKEN_EXPIRED);
            } else {
                throw new AppException(ErrorType.UNAUTHORIZED_INVALID_TOKEN);
            }
        }
    }

    @Transactional
    public void invalidate(String token) {
        invalidateAccessToken(token);
        Long userId = jwtUtil.getUserIdByToken(token);
        invalidateRefreshToken(userId);
    }

    private void invalidateAccessToken(String token) {
        var expiresAt = jwtUtil.getExpiryByToken(token);
        Duration expiresAfter = Duration.between(LocalDateTime.now(), expiresAt);
        redisTemplate.opsForValue().set(token, "LOGOUT");
        redisTemplate.expire(token, expiresAfter);
    }

    private void invalidateRefreshToken(Long userId) {
        userTokenUpdater.updateExpiresAt(userId, LocalDateTime.now());
    }
}
