package org.bogus.groove.client.auth;

import org.bogus.groove_auth.endpoint.user.UserController;
import org.springframework.stereotype.Component;

@Component
public class AuthClient {
    private final UserController userController;

    protected AuthClient(UserController userController) {
        this.userController = userController;
    }

    public UserInfo getUserInfo(String token) {
        return new UserInfo(userController.getSelfInfo(token).getData());
    }
}
