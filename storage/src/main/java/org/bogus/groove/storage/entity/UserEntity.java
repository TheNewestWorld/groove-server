package org.bogus.groove.storage.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.common.enumeration.UserRole;

@Entity
@Table(
    name = "`user`",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "provider"}),
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

    @Setter
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Setter
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "authentication_flag")
    private boolean isAuthenticated;

    @Column(name = "authenticated_at")
    private LocalDateTime authenticatedAt;

    @Setter
    @Column(name = "active")
    private boolean active = true;

    public UserEntity(String email, String password, String nickname, ProviderType providerType, UserRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.providerType = providerType;
        this.role = role;
    }

    public void updateAuthentication(boolean isAuthenticated, LocalDateTime authenticatedAt) {
        this.isAuthenticated = isAuthenticated;
        this.authenticatedAt = authenticatedAt;
        this.role = isAuthenticated ? UserRole.USER : UserRole.INACTIVE;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
