package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;

    public String refresh(long userId, String refreshToken) {
        tokenValidator.validateRefreshable(userId, refreshToken);
        return tokenGenerator.generateAccessToken(userId);
    }

    public void logout(String token) {
        tokenValidator.invalidate(token);
    }
}
