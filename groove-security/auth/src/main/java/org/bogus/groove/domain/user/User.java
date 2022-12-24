package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.common.enumeration.UserRole;
import org.bogus.groove.storage.entity.UserEntity;

@Getter
public class User {
    private final Long id;
    private final String email;
    private final String nickname;
    private final ProviderType providerType;
    private final UserRole role;
    private final boolean isAuthenticated;
    private final LocalDateTime authenticatedAt;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    User(UserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.providerType = entity.getProviderType();
        this.role = entity.getRole();
        this.isAuthenticated = entity.isAuthenticated();
        this.authenticatedAt = entity.getAuthenticatedAt();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
