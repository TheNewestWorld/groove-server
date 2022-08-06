package org.bogus.groove_auth.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginResult {
    private final UserInfo userInfo;
    private final String accessToken;
    private final String refreshToken;
}
