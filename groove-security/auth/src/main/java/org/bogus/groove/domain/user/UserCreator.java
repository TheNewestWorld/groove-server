package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.UserEntity;
import org.bogus.groove.storage.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(UserRegisterParam param) {
        var entity = userRepository.save(
            new UserEntity(
                param.getEmail(),
                passwordEncoder.encode(param.getPassword().getValue()),
                param.getUserType(),
                param.getNickname())
        );
        return new User(entity);
    }
}
