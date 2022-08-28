package org.bogus.groove.config;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.authority.Authority;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityCode implements GrantedAuthority {
    private final Authority authority;

    @Override
    public String getAuthority() {
        return "ROLE_" + authority.name();
    }
}
