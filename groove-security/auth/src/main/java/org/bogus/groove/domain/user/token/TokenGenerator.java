package org.bogus.groove.domain.user.token;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private final JwtUtil jwtUtil;
    private final UserTokenUpdater userTokenUpdater;

    public String generateAccessToken(Long userId) {
        return jwtUtil.generateAccessToken(userId).getToken();
    }

    public String generateRefreshToken(Long userId) {
        UserToken token = jwtUtil.generateRefreshToken(userId);
        userTokenUpdater.upsert(userId, token.getToken(), token.getExpiresAt());
        return token.getToken();
    }
}
