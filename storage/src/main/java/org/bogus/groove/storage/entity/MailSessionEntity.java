package org.bogus.groove.storage.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mail_session")
@Getter
@NoArgsConstructor
public class MailSessionEntity extends BaseEntity {
    @Column(name = "ref_user_id")
    private long userId;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    public MailSessionEntity(long userId, String sessionKey, LocalDateTime expiredAt) {
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.expiredAt = expiredAt;
    }
}
