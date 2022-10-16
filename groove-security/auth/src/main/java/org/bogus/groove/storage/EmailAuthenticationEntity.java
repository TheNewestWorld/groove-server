package org.bogus.groove.storage;

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

    @Column(name = "authentication_flag")
    private boolean isAuthenticated = false;

    @Column(name = "authenticated_at")
    private LocalDateTime authenticatedAt;

    public EmailAuthenticationEntity(long userId, String sessionKey, LocalDateTime expiredAt) {
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.expiredAt = expiredAt;
    }

    public void updateAuthentication(boolean isAuthenticated, LocalDateTime authenticatedAt) {
        this.isAuthenticated = isAuthenticated;
        this.authenticatedAt = authenticatedAt;
    }

}
