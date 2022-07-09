package org.bogus.groove_auth.domain.user.token;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private final JwtUtil jwtUtil;
    private final UserTokenUpdater userTokenUpdater;

    public String generateAccessToken(Long userId) {
        return jwtUtil.generateAccessToken(userId);
    }

    @Transactional
    public String generateRefreshToken(Long userId) {
        String refreshToken = jwtUtil.generateRefreshToken(userId);
        LocalDateTime expiresAt = jwtUtil.getExpiryByToken(refreshToken);
        userTokenUpdater.upsert(userId, refreshToken, expiresAt);
        return refreshToken;
    }
}
