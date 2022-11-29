package org.bogus.groove.domain.user.token;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.UserTokenEntity;
import org.bogus.groove.storage.repository.UserTokenRepository;
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
    public void update(Long userId, LocalDateTime expiresAt) {
        var entity = userTokenRepository.findByUserId(userId);
        if (entity.isPresent() && entity.get().getExpiresAt().isAfter(LocalDateTime.now())) {
            entity.get().setExpiresAt(expiresAt);
        }
    }
}
