package org.bogus.groove.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bogus.groove.domain.user.UserType;

@Entity
@Table(
    name = "`user`",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "type"}),
        @UniqueConstraint(columnNames = {"nickname"})
    }
)
@Getter
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @Setter
    @Column(name = "nickname")
    private String nickname;

    @Setter
    @Column(name = "ref_profile_id")
    private Long profileId;

    public UserEntity(String email, String password, UserType type, String nickname) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.nickname = nickname;
    }
}
