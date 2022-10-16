package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.UserEntity;
import org.bogus.groove.storage.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository repository;

    @Transactional
    public User update(Long userId, boolean isAuthenticated, LocalDateTime authenticatedAt) {
        UserEntity entity = repository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));

        entity.updateAuthentication(isAuthenticated, authenticatedAt);
        return new User(entity);
    }
}
