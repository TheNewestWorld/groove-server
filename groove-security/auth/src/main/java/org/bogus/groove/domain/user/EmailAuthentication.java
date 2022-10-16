package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.EmailAuthenticationEntity;

@Getter
@RequiredArgsConstructor
public class EmailAuthentication {
    private long id;
    private long userId;
    private String sessionKey;
    private LocalDateTime expiredAt;
    private boolean isAuthenticated;
    private LocalDateTime authenticatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmailAuthentication(EmailAuthenticationEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.sessionKey = entity.getSessionKey();
        this.expiredAt = entity.getExpiredAt();
        this.isAuthenticated = entity.isAuthenticated();
        this.authenticatedAt = entity.getAuthenticatedAt();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
