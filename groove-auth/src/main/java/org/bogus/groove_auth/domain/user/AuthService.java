package org.bogus.groove_auth.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.domain.user.token.TokenGenerator;
import org.bogus.groove_auth.domain.user.token.TokenValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final UserInfoFinder userInfoFinder;
    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;

    public String refresh(String accessToken, String refreshToken) {
        var userId = userInfoFinder.find(accessToken).getId();
        tokenValidator.validateRefreshable(userId, refreshToken);
        return tokenGenerator.generateAccessToken(userId);
    }

    public void logout(String token) {
        tokenValidator.invalidate(token);
    }
}
