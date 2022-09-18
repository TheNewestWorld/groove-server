package org.bogus.groove.config.oauth;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class OAuth2UserMapper {
    public static Optional<OAuth2UserInfo> map(Authentication authentication) {
        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            return Optional.empty();
        }

        var oauth2Authentication = (OAuth2AuthenticationToken) authentication;
        switch (oauth2Authentication.getAuthorizedClientRegistrationId()) {
            case "google":
                return Optional.of(new GoogleUserInfo(oauth2Authentication));
            default:
                return Optional.empty();
        }
    }
}
