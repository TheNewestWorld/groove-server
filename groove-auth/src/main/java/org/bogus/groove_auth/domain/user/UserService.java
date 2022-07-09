package org.bogus.groove_auth.domain.user;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.UserInfo;
import org.bogus.groove.common.enumeration.Authority;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove_auth.domain.user.authority.UserAuthorityUpdater;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreator userCreator;
    private final UserAuthorityUpdater userAuthorityUpdater;
    private final UserInfoFinder userInfoFinder;

    @Transactional
    public UserInfo register(String email) {
        var user = userCreator.create(email, UserType.DEFAULT);
        var authorities = userAuthorityUpdater.update(user.getId(), Authority.DEFAULT);
        return new UserInfo(
            user.getId(),
            user.getEmail(),
            user.getType(),
            authorities
        );
    }

    public UserInfo getSelf(String token) {
        return userInfoFinder.find(token);
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }
}
