package org.bogus.groove_auth.endpoint.user;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoGetResponse {
    private final Long id;
    private final String email;
    private final String type;
    private final List<String> authorities;
}
