package org.bogus.groove.config.oauth;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.UserInfo;
import org.bogus.groove.domain.user.UserInfoFinder;
import org.bogus.groove.domain.user.UserRegister;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRegister userRegister;
    private final UserInfoFinder userInfoFinder;
    private final TokenGenerator tokenGenerator;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        var oauth2UserInfo = OAuth2UserMapper.map(authentication);
        if (oauth2UserInfo.isEmpty()) {
            authentication.setAuthenticated(false);
            response.sendRedirect(getOauth2FailedRedirectUri().toUriString());
            return;
        }

        UserInfo userInfo = findUserInfoOrRegister(oauth2UserInfo.get());

        var accessToken = tokenGenerator.generateAccessToken(userInfo.getId());
        var refreshToken = tokenGenerator.generateRefreshToken(userInfo.getId());

        // TODO SecurityConfig 에서 redirectUrl 지정
        response.sendRedirect(getOAuth2SucceedRedirectUri(accessToken, refreshToken).toUriString());
    }

    private UserInfo findUserInfoOrRegister(OAuth2UserInfo oauth2UserInfo) {
        var email = oauth2UserInfo.getEmail();
        var userType = oauth2UserInfo.getUserType();

        return userInfoFinder
            .findOrNull(email, userType)
            .orElseGet(() -> userRegister.register(email, null, userType));
    }

    private UriComponents getOauth2FailedRedirectUri() {
        return UriComponentsBuilder
            .fromPath("/oauth2/fail")
            .build();
    }

    private UriComponents getOAuth2SucceedRedirectUri(String accessToken, String refreshToken) {
        return UriComponentsBuilder
            .fromPath("/oauth2/success")
            .queryParam("accessToken", accessToken)
            .queryParam("refreshToken", refreshToken)
            .build();
    }
}
