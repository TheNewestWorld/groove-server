package org.bogus.groove.storage;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "authentication_flag")
    private boolean isAuthenticated;

    @Column(name = "authenticated_at")
    private LocalDateTime authenticatedAt;

    public UserEntity(String email, String password, UserType type, String nickname) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.nickname = nickname;
    }

    public void updateAuthentication(boolean isAuthenticated, LocalDateTime authenticatedAt) {
        this.isAuthenticated = isAuthenticated;
        this.authenticatedAt = authenticatedAt;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
