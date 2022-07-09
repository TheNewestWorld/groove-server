package org.bogus.groove_auth.endpoint.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenRefreshResponse {
    private final String accessToken;
}
