package org.bogus.groove.config.authentication;

import lombok.Getter;
import org.bogus.groove.domain.user.Password;

@Getter
public class LoginRequest {
    private String email;
    private Password password;
}
