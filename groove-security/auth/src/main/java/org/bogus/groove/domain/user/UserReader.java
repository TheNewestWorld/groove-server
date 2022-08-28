package org.bogus.groove.domain.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;

    public User read(Long userId) {
        return readOrNull(userId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
    }

    public Optional<User> readOrNull(String email, UserType userType) {
        return userRepository.findByEmailAndType(email, userType).map(User::new);
    }

    public Optional<User> readOrNull(Long userId) {
        return userRepository.findById(userId).map(User::new);
    }
}
