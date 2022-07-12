package org.bogus.groove_auth.domain.user.token;

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
        return jwtUtil.generateAccessToken(userId).getToken();
    }

    @Transactional
    public String generateRefreshToken(Long userId) {
        UserToken token = jwtUtil.generateRefreshToken(userId);
        userTokenUpdater.upsert(userId, token.getToken(), token.getExpiresAt());
        return token.getToken();
    }
}
