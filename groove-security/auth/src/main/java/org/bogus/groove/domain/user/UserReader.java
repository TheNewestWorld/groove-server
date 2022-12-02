package org.bogus.groove.domain.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ProviderType;
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

    public Optional<User> readOrNull(String email, ProviderType providerType) {
        return userRepository.findByEmailAndProviderType(email, providerType).map(User::new);
    }

    public Optional<User> readOrNull(Long userId) {
        return userRepository.findById(userId).map(User::new);
    }

    public Optional<User> readOrNull(String nickname) {
        return userRepository.findByNickname(nickname).map(User::new);
    }
}
