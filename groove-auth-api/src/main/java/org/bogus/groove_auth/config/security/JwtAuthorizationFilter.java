package org.bogus.groove_auth.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.domain.user.UserInfoFinder;
import org.bogus.groove_auth.domain.user.token.TokenValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final TokenValidator tokenValidator;
    private final UserInfoFinder userInfoFinder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        if (isSupported(request)) {
            var accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            tokenValidator.validate(accessToken);

            var userDetails = new CustomUserDetails(userInfoFinder.find(accessToken));
            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                )
            );
        }

        filterChain.doFilter(request, response);
    }

    private boolean isSupported(HttpServletRequest request) {
        var accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        return accessToken != null;
    }
}
