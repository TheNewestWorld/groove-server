package org.bogus.groove_auth.domain.user.authority;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    USER(SecurityCode.USER),
    TRAINER(SecurityCode.TRAINER),
    ADMIN(SecurityCode.ADMIN);

    @Getter
    private final String securityCode;

    Authority(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getAuthority() {
        return this.securityCode;
    }

    public static class SecurityCode {
        public static final String USER = "ROLE_USER";
        public static final String TRAINER = "ROLE_TRAINER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}

