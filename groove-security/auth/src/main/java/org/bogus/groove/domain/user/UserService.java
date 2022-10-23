package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRegister userRegister;
    private final UserInfoFinder userInfoFinder;

    public void register(UserRegisterParam param) {
        userRegister.register(param);
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }
}
