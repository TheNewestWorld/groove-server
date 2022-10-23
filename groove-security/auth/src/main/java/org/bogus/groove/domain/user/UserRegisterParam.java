package org.bogus.groove.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegisterParam {
    private final String email;
    private final String password;
    private final UserType userType = UserType.GROOVE;
    private final String nickname;
}
