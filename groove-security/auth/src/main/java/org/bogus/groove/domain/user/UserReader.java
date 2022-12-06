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

    public User read(String email, ProviderType providerType) {
        return readOrNull(email, providerType).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
    }

    public Optional<User> readOrNull(String email, ProviderType providerType) {
        return userRepository.findByEmailAndProviderTypeAndActiveIsTrue(email, providerType).map(User::new);
    }

    public Optional<User> readOrNull(Long userId) {
        return userRepository.findByIdAndActiveIsTrue(userId).map(User::new);
    }

    public boolean isExists(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public boolean isExists(String email, ProviderType providerType) {
        return userRepository.existsByEmailAndProviderType(email, providerType);
    }
}
