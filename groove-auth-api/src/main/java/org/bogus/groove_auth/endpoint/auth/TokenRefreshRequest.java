package org.bogus.groove_auth.endpoint.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {
    private String refreshToken;
}
