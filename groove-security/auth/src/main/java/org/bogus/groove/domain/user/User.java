package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.storage.UserEntity;

@Getter
public class User {
    private final Long id;
    private final String email;
    private final UserType type;
    private final boolean isAuthenticated;
    private final LocalDateTime authenticatedAt;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    User(UserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.type = entity.getType();
        this.isAuthenticated = entity.isAuthenticated();
        this.authenticatedAt = entity.getAuthenticatedAt();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
