package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.storage.UserEntity;

@Getter
public class User {
    private final Long id;
    private final String email;
    private final String nickname;
    private final UserType type;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    User(UserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.type = entity.getType();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
