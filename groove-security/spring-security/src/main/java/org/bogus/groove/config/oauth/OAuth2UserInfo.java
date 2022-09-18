package org.bogus.groove.config.oauth;

import org.bogus.groove.domain.user.UserType;

public interface OAuth2UserInfo {
    UserType getUserType();

    String getEmail();

    String getName();

    String getProfileImage();

    String getUserId();
}
