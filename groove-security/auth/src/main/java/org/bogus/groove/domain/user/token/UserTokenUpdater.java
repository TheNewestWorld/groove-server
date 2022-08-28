package org.bogus.groove.domain.user.token;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove.storage.UserTokenEntity;
import org.bogus.groove.storage.UserTokenRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTokenUpdater {
    private final UserTokenRepository userTokenRepository;

    @Transactional
    public void upsert(Long userId, String refreshToken, LocalDateTime expiresAt) {
        var entity = userTokenRepository.findByUserId(userId)
            .orElse(new UserTokenEntity(userId, null, null));

        entity.setRefreshToken(refreshToken);
        entity.setExpiresAt(expiresAt);

        userTokenRepository.save(entity);
    }

    @Transactional
    public void updateExpiresAt(Long userId, LocalDateTime expiresAt) {
        var entity = userTokenRepository.findByUserId(userId)
            .orElseThrow(() -> new UnauthorizedException(ErrorType.UNAUTHORIZED_NOT_FOUND_USER_TOKEN));

        entity.setExpiresAt(expiresAt);
    }
}
