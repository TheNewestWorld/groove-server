package org.bogus.groove.domain.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;

    public User read(Long userId) {
        return readOrNull(userId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
    }

    public User read(String email, UserType userType) {
        return readOrNull(email, userType).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
    }

    public Optional<User> readOrNull(String email, UserType userType) {
        return userRepository.findByEmailAndTypeAndActiveIsTrue(email, userType).map(User::new);
    }

    public Optional<User> readOrNull(Long userId) {
        return userRepository.findByIdAndActiveIsTrue(userId).map(User::new);
    }

    public boolean isExists(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public boolean isExists(String email, UserType userType) {
        return userRepository.existsByEmailAndType(email, userType);
    }
}
