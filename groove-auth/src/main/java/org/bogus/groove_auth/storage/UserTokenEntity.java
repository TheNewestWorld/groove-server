package org.bogus.groove_auth.storage;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "user_token",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ref_user_id", "refresh_token"})
    }
)
@Getter
@NoArgsConstructor
public class UserTokenEntity extends BaseEntity {
    @Column(name = "ref_user_id")
    private Long userId;

    @Setter
    @Column(name = "refresh_token")
    private String refreshToken;

    @Setter
    @Column(name = "expiresAt")
    private LocalDateTime expiresAt;

    public UserTokenEntity(Long userId, String refreshToken, LocalDateTime expiresAt) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }
}
