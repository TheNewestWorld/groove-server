package org.bogus.groove.config.authentication;

import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove.config.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
    private final PasswordEncoder passwordEncoder;

    public CustomDaoAuthenticationProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var userDetails = getUserDetailsService().loadUserByUsername(authentication.getName());
        if (passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                ((CustomUserDetails) userDetails).erasePassword(),
                null,
                userDetails.getAuthorities()
            );
        } else {
            throw new UnauthorizedException(ErrorType.UNAUTHORIZED_LOGIN_REQUEST);
        }
    }
}
