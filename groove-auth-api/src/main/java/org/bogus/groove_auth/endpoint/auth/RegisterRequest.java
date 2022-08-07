package org.bogus.groove_auth.endpoint.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove_auth.domain.user.UserRegisterParam;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;

    public UserRegisterParam toParam() {
        return new UserRegisterParam(email, password);
    }
}
