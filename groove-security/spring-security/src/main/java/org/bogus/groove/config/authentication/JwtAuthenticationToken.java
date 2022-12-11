package org.bogus.groove.config.authentication;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final UserDetails userDetails;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, UserDetails userDetails) {
        super(authorities);
        this.userDetails = userDetails;
    }

    @Override
    public Object getCredentials() {
        return super.getAuthorities();
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }
}
