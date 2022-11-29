package org.bogus.groove.domain.user;

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
    private final String nickName;
    private final String profileUri;
    private final List<Authority> authorities;
}
