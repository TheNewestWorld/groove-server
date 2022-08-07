package org.bogus.groove_auth.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegisterParam {
    private final String email;
    private final String password;
}
