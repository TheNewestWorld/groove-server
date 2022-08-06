package org.bogus.groove_auth.domain.user;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.domain.user.authority.Authority;
import org.bogus.groove_auth.domain.user.authority.UserAuthorityUpdater;
import org.bogus.groove_auth.domain.user.token.TokenValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreator userCreator;
    private final UserAuthorityUpdater userAuthorityUpdater;
    private final UserInfoFinder userInfoFinder;
    private final TokenValidator tokenValidator;

    @Transactional
    public UserInfo register(String email) {
        var user = userCreator.create(email, UserType.DEFAULT);
        var authorities = userAuthorityUpdater.update(user.getId(), List.of(Authority.USER));
        return new UserInfo(
            user.getId(),
            user.getEmail(),
            user.getType(),
            authorities
        );
    }

    public UserInfo getSelf(String token) {
        tokenValidator.validate(token);
        return userInfoFinder.find(token);
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }
}
