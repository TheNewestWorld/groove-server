package org.bogus.groove_auth.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.UserType;

@Entity
@Table(
    name = "`user`",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "type"})}
)
@Getter
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;

    public UserEntity(String email, UserType type) {
        this.email = email;
        this.type = type;
    }
}
