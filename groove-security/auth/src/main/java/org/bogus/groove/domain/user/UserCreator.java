package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.UserEntity;
import org.bogus.groove.storage.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;

    public User create(UserRegisterParam param, UserType userType) {
        var entity = userRepository.save(
            new UserEntity(
                param.getEmail(),
                param.getPassword(),
                userType
            )
        );
        return new User(entity);
    }
}
