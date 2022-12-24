package org.bogus.groove.endpoint.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.UserInfoFinder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InternalUserController {
    private final UserInfoFinder userInfoFinder;

    public InternalUserInfoGetResponse getUserInfo(Long userId) {
        var result = userInfoFinder.find(userId);
        return new InternalUserInfoGetResponse(
            result.getId(),
            result.getEmail(),
            result.getProviderType(),
            result.getNickName(),
            result.getProfileUri(),
            result.getRole()
        );
    }

    public InternalUserInfoGetResponse getUserInfo(String token) {
        var result = userInfoFinder.find(token);
        return new InternalUserInfoGetResponse(
            result.getId(),
            result.getEmail(),
            result.getProviderType(),
            result.getNickName(),
            result.getProfileUri(),
            result.getRole()
        );
    }
}
