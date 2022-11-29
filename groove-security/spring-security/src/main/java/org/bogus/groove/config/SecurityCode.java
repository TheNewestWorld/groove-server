package org.bogus.groove.config;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.Authority;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityCode implements GrantedAuthority {
    private final Authority authority;

    // "ROLE_"  + Authority.name
    public static final String INACTIVE = "ROLE_INACTIVE";
    public static final String USER = "ROLE_USER";
    public static final String TRAINER = "ROLE_TRAINER";
    public static final String ADMIN = "ROLE_ADMIN";

    @Override
    public String getAuthority() {
        return "ROLE_" + authority.name();
    }
}
