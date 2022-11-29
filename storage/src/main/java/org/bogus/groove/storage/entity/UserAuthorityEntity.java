package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.Authority;

@Entity
@Table(
    name = "user_authority",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ref_user_id", "authority"})
    }
)
@Getter
@NoArgsConstructor
public class UserAuthorityEntity extends BaseEntity {
    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public UserAuthorityEntity(Long userId, Authority authority) {
        this.userId = userId;
        this.authority = authority;
    }
}
