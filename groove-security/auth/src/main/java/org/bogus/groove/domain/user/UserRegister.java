package org.bogus.groove.domain.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.authority.Authority;
import org.bogus.groove.domain.user.authority.UserAuthorityUpdater;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegister {
    private final UserCreator userCreator;
    private final UserAuthorityUpdater userAuthorityUpdater;

    public UserInfo register(String email, String password, UserType userType) {
        var user = userCreator.create(email, password, userType);
        var authorities = userAuthorityUpdater.update(user.getId(), List.of(Authority.USER));
        return new UserInfo(
            user.getId(),
            user.getEmail(),
            user.getType(),
            authorities
        );
    }
}
