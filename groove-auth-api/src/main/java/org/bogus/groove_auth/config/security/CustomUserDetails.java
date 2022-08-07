package org.bogus.groove_auth.config.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.domain.user.UserInfo;
import org.bogus.groove_auth.domain.user.UserType;
import org.bogus.groove_auth.domain.user.authority.Authority;
import org.bogus.groove_auth.storage.UserAuthorityEntity;
import org.bogus.groove_auth.storage.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final long userId;
    private final String email;
    @Getter
    private final UserType type;
    private final List<Authority> authorities;
    private String password;

    public CustomUserDetails(UserInfo userInfo) {
        this.userId = userInfo.getId();
        this.email = userInfo.getEmail();
        this.type = userInfo.getType();
        this.authorities = userInfo.getAuthorities();
    }

    public CustomUserDetails(UserEntity userEntity, Collection<UserAuthorityEntity> userAuthorityEntities) {
        this.userId = userEntity.getId();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.type = userEntity.getType();
        this.authorities = userAuthorityEntities.stream().map(UserAuthorityEntity::getAuthority).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public long getUserId() {
        return userId;
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
