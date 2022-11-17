package org.bogus.groove.endpoint.user;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InternalUserInfoGetResponse {
    private final Long id;
    private final String email;
    private final String type;
    private final String nickname;
    private final String profileUri;
    private final List<String> authorities;
}
