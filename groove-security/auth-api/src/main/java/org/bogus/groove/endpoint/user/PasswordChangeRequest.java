package org.bogus.groove.endpoint.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.bogus.groove.common.Password;

@Getter
public class PasswordChangeRequest {
    private String sessionKey;
    @Schema(implementation = String.class)
    private Password password;
}
