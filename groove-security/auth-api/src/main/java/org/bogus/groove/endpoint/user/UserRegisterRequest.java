package org.bogus.groove.endpoint.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String email;
    private String password;
    private String nickname;
}
