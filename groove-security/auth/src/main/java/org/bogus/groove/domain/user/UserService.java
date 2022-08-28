package org.bogus.groove.domain.user;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.authority.Authority;
import org.bogus.groove.domain.user.authority.UserAuthorityUpdater;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreator userCreator;
    private final UserAuthorityUpdater userAuthorityUpdater;
    private final UserInfoFinder userInfoFinder;

    @Transactional
    public UserInfo register(UserRegisterParam param) {
        var user = userCreator.create(param, UserType.GROOVE);
        var authorities = userAuthorityUpdater.update(user.getId(), List.of(Authority.USER));
        return new UserInfo(
            user.getId(),
            user.getEmail(),
            user.getType(),
            authorities
        );
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }
}
