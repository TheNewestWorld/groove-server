package org.bogus.groove.endpoint.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InternalUserController {
    private final UserService userService;

    public InternalUserInfoGetResponse getUserInfo(Long userId) {
        var result = userService.getUserInfo(userId);
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
