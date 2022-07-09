package org.bogus.groove_auth.domain.user.token;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.storage.UserTokenEntity;
import org.bogus.groove_auth.storage.UserTokenRepository;
import org.bogus.groove_auth.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private final JwtUtil jwtUtil;
    private final UserTokenRepository userTokenRepository;

    public String generateAccessToken(Long userId) {
        return jwtUtil.generateAccessToken(userId);
    }

    @Transactional
    public String generateRefreshToken(Long userId) {
        String refreshToken = jwtUtil.generateRefreshToken(userId);
        upsertUserToken(userId, refreshToken);
        return refreshToken;
    }

    private void upsertUserToken(Long userId, String refreshToken) {
        var entity = userTokenRepository.findByUserId(userId)
            .orElse(new UserTokenEntity(userId, null, null));

        entity.setRefreshToken(refreshToken);
        entity.setExpiredAt(jwtUtil.getExpiryByToken(refreshToken));

        userTokenRepository.save(entity);
    }
}
