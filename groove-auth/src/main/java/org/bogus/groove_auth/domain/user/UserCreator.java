package org.bogus.groove_auth.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove_auth.storage.UserEntity;
import org.bogus.groove_auth.storage.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;

    public User create(String email, UserType userType) {
        var entity = userRepository.save(new UserEntity(email, userType));
        return new User(entity);
    }
}
