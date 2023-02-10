package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.MailSessionEntity;

@Getter
@RequiredArgsConstructor
public class MailSession {
    private long id;
    private long userId;
    private String sessionKey;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MailSession(MailSessionEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.sessionKey = entity.getSessionKey();
        this.expiredAt = entity.getExpiredAt();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
