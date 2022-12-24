package org.bogus.groove.storage.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_authentication")
@Getter
@NoArgsConstructor
public class EmailAuthenticationEntity extends BaseEntity {
    @Column(name = "ref_user_id")
    private long userId;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "verified")
    private boolean verified = false;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    public EmailAuthenticationEntity(long userId, String sessionKey, LocalDateTime expiredAt) {
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.expiredAt = expiredAt;
    }

    public void updateAuthentication(boolean verified, LocalDateTime verifiedAt) {
        this.verified = verified;
        this.verifiedAt = verifiedAt;
    }

}
