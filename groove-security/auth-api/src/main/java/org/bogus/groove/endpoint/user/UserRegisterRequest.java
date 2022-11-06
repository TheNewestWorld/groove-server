package org.bogus.groove.endpoint.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.Password;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String email;
    @Schema(implementation = String.class)
    private Password password;
    private String nickname;
}
