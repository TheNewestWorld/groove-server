package org.bogus.groove.endpoint.user;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.UserType;
import org.bogus.groove.domain.user.authority.Authority;

@Getter
@RequiredArgsConstructor
public class UserInfoGetResponse {
    private final Long id;
    private final String email;
    private final UserType type;
    private final List<Authority> authorities;
}
