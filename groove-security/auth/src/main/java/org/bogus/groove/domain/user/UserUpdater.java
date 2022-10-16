package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.UserEntity;
import org.bogus.groove.storage.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User update(Long userId, boolean isAuthenticated, LocalDateTime authenticatedAt) {
        UserEntity entity = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));

        entity.updateAuthentication(isAuthenticated, authenticatedAt);
        return new User(entity);
    }

    @Transactional
    public void update(Long id, String password) {
        var entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
        var encodedPassword = passwordEncoder.encode(password);
        entity.updatePassword(encodedPassword);
    }
}
