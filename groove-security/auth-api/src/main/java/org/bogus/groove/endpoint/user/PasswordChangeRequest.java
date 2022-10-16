package org.bogus.groove.endpoint.user;

import lombok.Getter;

@Getter
public class PasswordChangeRequest {
    private String sessionKey;
    private String password;
}
