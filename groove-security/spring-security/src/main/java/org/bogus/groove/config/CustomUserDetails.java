package org.bogus.groove.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.UserInfo;
import org.bogus.groove.domain.user.UserType;
import org.bogus.groove.storage.UserAuthorityEntity;
import org.bogus.groove.storage.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    @Getter
    private final long userId;
    @Getter
    private final String email;
    @Getter
    private final UserType type;
    private final List<SecurityCode> securityCodes;
    private String password;

    public CustomUserDetails(UserInfo userInfo) {
        this.userId = userInfo.getId();
        this.email = userInfo.getEmail();
        this.type = userInfo.getType();
        this.securityCodes = userInfo.getAuthorities().stream().map(SecurityCode::new).collect(Collectors.toList());
    }

    public CustomUserDetails(UserEntity userEntity, Collection<UserAuthorityEntity> userAuthorityEntities) {
        this.userId = userEntity.getId();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.type = userEntity.getType();
        this.securityCodes =
            userAuthorityEntities.stream().map((entity) -> new SecurityCode(entity.getAuthority())).collect(Collectors.toList());
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

    public CustomUserDetails erasePassword() {
        this.password = null;
        return this;
    }
}
