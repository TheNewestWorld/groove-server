package org.bogus.groove.config.authorization;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.domain.user.UserInfoFinder;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Deprecated
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

            var userInfo = userInfoFinder.find(accessToken);
            var userDetails = new GrooveUserDetails(
                userInfo.getId(),
                userInfo.getEmail(),
                null,
                userInfo.getProviderType(),
                userInfo.getRole()
            );

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
