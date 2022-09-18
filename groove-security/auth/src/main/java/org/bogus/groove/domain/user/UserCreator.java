package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.UserEntity;
import org.bogus.groove.storage.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;

    public User create(String email, String password, UserType userType) {
        var entity = userRepository.save(new UserEntity(email, password, userType));
        return new User(entity);
    }
}
