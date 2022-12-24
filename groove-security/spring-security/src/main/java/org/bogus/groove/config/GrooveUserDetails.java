package org.bogus.groove.config;

import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.common.enumeration.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class GrooveUserDetails implements UserDetails {
    @Getter
    private final long userId;
    @Getter
    private final String email;
    @Getter
    private final ProviderType type;
    private final List<SecurityCode> securityCodes;
    private String password;

    public GrooveUserDetails(
        Long id,
        String email,
        String password,
        ProviderType providerType,
        UserRole role
    ) {
        this.userId = id;
        this.email = email;
        this.password = password;
        this.type = providerType;
        this.securityCodes = List.of(new SecurityCode(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return securityCodes;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public GrooveUserDetails erasePassword() {
        this.password = null;
        return this;
    }
}
