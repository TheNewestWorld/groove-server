package org.bogus.groove_auth.storage;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.Authority;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "user_authority",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ref_user_id", "authority"})
    }
)
@Getter
@NoArgsConstructor
public class UserAuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "reg_ts", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "upd_ts", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserAuthorityEntity(Long userId, Authority authority) {
        this.userId = userId;
        this.authority = authority;
    }
}
