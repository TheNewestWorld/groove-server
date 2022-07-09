package org.bogus.groove_auth.endpoint.user;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.Authority;
import org.bogus.groove.common.enumeration.UserType;

@Getter
@RequiredArgsConstructor
public class UserInfoGetResponse {
    private final Long id;
    private final String email;
    private final UserType type;
    private final List<Authority> authorities;
}
