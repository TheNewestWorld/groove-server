package org.bogus.groove.config.authentication;

import org.bogus.groove.common.Password;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.ForbiddenException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GrooveAuthenticationProvider extends DaoAuthenticationProvider {
    private final PasswordEncoder passwordEncoder;

    public GrooveAuthenticationProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var userDetails = getUserDetailsService().loadUserByUsername(authentication.getName());

        var rawPassword = ((Password) authentication.getCredentials()).getValue();
        if (passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            if (isInactive(userDetails)) {
                throw new ForbiddenException(ErrorType.FORBIDDEN_EMAIL_NOT_AUTHENTICATED);
            }

            return new UsernamePasswordAuthenticationToken(
                ((GrooveUserDetails) userDetails).erasePassword(),
                null,
                userDetails.getAuthorities()
            );
        } else {
            throw new UnauthorizedException(ErrorType.UNAUTHORIZED_LOGIN_REQUEST);
        }
    }

    private static boolean isInactive(UserDetails userDetails) {
        return userDetails.getAuthorities().stream().anyMatch((authority) -> authority.getAuthority().equals(SecurityCode.INACTIVE));
    }
}
