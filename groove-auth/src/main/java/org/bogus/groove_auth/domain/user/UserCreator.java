package org.bogus.groove_auth.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.storage.UserEntity;
import org.bogus.groove_auth.storage.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(UserRegisterParam param, UserType userType) {
        var entity = userRepository.save(
            new UserEntity(
                param.getEmail(),
                passwordEncoder.encode(param.getPassword()),
                userType
            )
        );
        return new User(entity);
    }
}
