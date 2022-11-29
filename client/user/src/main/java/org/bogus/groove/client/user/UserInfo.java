package org.bogus.groove.client.user;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.Authority;
import org.bogus.groove.common.enumeration.UserType;

@Getter
@RequiredArgsConstructor
public class UserInfo {
    private final Long id;
    private final String email;
    private final UserType type;
    private final String nickname;
    private final String profileUri;
    private final List<Authority> authorities;
}
