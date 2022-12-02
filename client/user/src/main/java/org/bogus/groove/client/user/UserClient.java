package org.bogus.groove.client.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.endpoint.user.InternalUserController;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClient {
    private final InternalUserController internalUserController;

    public UserInfo get(Long userId) {
        var result = internalUserController.getUserInfo(userId);
        return new UserInfo(
            result.getId(),
            result.getEmail(),
            result.getProviderType(),
            result.getNickname(),
            result.getProfileUri(),
            result.getRole()
        );
    }
}
