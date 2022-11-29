package org.bogus.groove.domain.user.token;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.UserTokenEntity;

@Getter
@RequiredArgsConstructor
public class UserToken {
    private final String token;
    private final LocalDateTime expiresAt;

    public UserToken(UserTokenEntity entity) {
        this.token = entity.getRefreshToken();
        this.expiresAt = entity.getExpiresAt();
    }
}
