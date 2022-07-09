package org.bogus.groove_auth.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove_auth.domain.user.token.TokenGenerator;
import org.bogus.groove_auth.domain.user.token.TokenValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final UserInfoFinder userInfoFinder;
    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;

    public LoginResult login(String email) {
        var userInfo = userInfoFinder.find(email, UserType.DEFAULT);
        var accessToken = tokenGenerator.generateAccessToken(userInfo.getId());
        var refreshToken = tokenGenerator.generateRefreshToken(userInfo.getId());

        return new LoginResult(userInfo, accessToken, refreshToken);
    }

    public String refresh(String accessToken, String refreshToken) {
        var userId = userInfoFinder.find(accessToken).getId();
        tokenValidator.validateRefreshing(userId, refreshToken);
        return tokenGenerator.generateAccessToken(userId);
    }
}
