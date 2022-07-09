package org.bogus.groove_auth.domain.user.token;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.storage.UserTokenEntity;

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
