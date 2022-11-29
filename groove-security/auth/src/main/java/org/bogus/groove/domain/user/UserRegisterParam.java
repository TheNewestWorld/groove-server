package org.bogus.groove.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.Password;
import org.bogus.groove.common.enumeration.UserType;

@Getter
@RequiredArgsConstructor
public class UserRegisterParam {
    private final String email;
    private final Password password;
    private final UserType userType = UserType.GROOVE;
    private final String nickname;
}
