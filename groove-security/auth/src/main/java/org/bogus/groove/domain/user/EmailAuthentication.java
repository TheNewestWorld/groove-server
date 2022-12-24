package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.EmailAuthenticationEntity;

@Getter
@RequiredArgsConstructor
public class EmailAuthentication {
    private long id;
    private long userId;
    private String sessionKey;
    private LocalDateTime expiredAt;
    private boolean verified;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmailAuthentication(EmailAuthenticationEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.sessionKey = entity.getSessionKey();
        this.expiredAt = entity.getExpiredAt();
        this.verified = entity.isVerified();
        this.verifiedAt = entity.getVerifiedAt();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
