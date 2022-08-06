package org.bogus.groove.client.auth;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.endpoint.user.UserInfoGetResponse;

@Getter
@RequiredArgsConstructor
public class UserInfo {
    private final Long id;
    private final String email;
    private final UserType type;
    private final List<Authority> authorities;

    public UserInfo(UserInfoGetResponse clientResponse) {
        this.id = clientResponse.getId();
        this.email = clientResponse.getEmail();
        this.type = UserType.ofOrNull(clientResponse.getType());
        this.authorities = clientResponse.getAuthorities().stream()
            .map(Authority::ofOrNull)
            .filter((Objects::nonNull))
            .collect(Collectors.toList());
    }
}
