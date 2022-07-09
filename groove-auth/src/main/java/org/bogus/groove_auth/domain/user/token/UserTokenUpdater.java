package org.bogus.groove_auth.domain.user.token;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.error.AppException;
import org.bogus.groove_auth.error.ErrorType;
import org.bogus.groove_auth.storage.UserTokenEntity;
import org.bogus.groove_auth.storage.UserTokenRepository;
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
            .orElseThrow(() -> new AppException(ErrorType.UNAUTHORIZED_NOT_FOUND_USER_TOKEN));

        entity.setExpiresAt(expiresAt);
    }
}
