package org.bogus.groove_auth.storage;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "user_token",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "ref_user_id", "refresh_token" })
    }
)
@Getter
@NoArgsConstructor
public class UserTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "ref_user_id")
    private Long userId;

    @Setter
    @Column(name = "refresh_token")
    private String refreshToken;

    @Setter
    @Column(name = "expiredAt")
    private LocalDateTime expiredAt;

    @Column(name = "reg_ts", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "upd_ts", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserTokenEntity(Long userId, String refreshToken, LocalDateTime expiredAt) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.expiredAt = expiredAt;
    }
}
