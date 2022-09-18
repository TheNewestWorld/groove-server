package org.bogus.groove.endpoint.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenRefreshResponse {
    private final String accessToken;
}
