package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.UserRole;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.entity.UserEntity;
import org.bogus.groove.storage.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void update(long userId, String nickname) {
        var entity = getEntity(userId);
        entity.setNickname(nickname);
    }

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

    @Transactional
    public void update(Long userId, UserRole userRole) {
        UserEntity entity = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));

        entity.setRole(userRole);
    }

    private UserEntity getEntity(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
    }
}
