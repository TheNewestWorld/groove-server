package org.bogus.groove.config.interceptor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.config.authentication.JwtAuthenticationToken;
import org.bogus.groove.domain.user.UserInfoFinder;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class SecurityContextInjectionInterceptor implements HandlerInterceptor {
    private final TokenValidator tokenValidator;
    private final UserInfoFinder userInfoFinder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isHandlerMethod(handler) && isSecuredMethod((HandlerMethod) handler)) {
            var accessToken = getToken(request);
            ignoreTokenExpired(() -> tokenValidator.validate(accessToken));

            var authenticatedToken = convertToAuthenticatedToken(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
        }
        return true;
    }

    private JwtAuthenticationToken convertToAuthenticatedToken(String accessToken) {
        var userInfo = userInfoFinder.find(accessToken);
        var token = new JwtAuthenticationToken(
            List.of(new SecurityCode(userInfo.getRole())),
            new GrooveUserDetails(
                userInfo.getId(),
                userInfo.getEmail(),
                null,
                userInfo.getProviderType(),
                userInfo.getRole()
            )
        );
        token.setAuthenticated(true);
        return token;
    }

    private boolean isSecuredMethod(HandlerMethod handler) {
        return handler.getMethodAnnotation(Secured.class) != null;
    }

    private boolean isHandlerMethod(Object handler) {
        return handler instanceof HandlerMethod;
    }

    private String getToken(HttpServletRequest request) {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            throw new UnauthorizedException(ErrorType.UNAUTHORIZED_NOT_FOUND_USER_TOKEN);
        }
        return token;
    }

    // TODO Exception 타입을 나누는게 좋아보임
    private void ignoreTokenExpired(Runnable action) {
        try {
            action.run();
        } catch (UnauthorizedException e) {
            if (!e.getError().equals(ErrorType.UNAUTHORIZED_TOKEN_EXPIRED)) {
                throw e;
            }
        }
    }
}
