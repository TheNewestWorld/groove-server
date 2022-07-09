package org.bogus.groove.endpoint.middleware;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.error.AppException;
import org.bogus.groove.error.ErrorType;
import org.bogus.groove_auth.endpoint.user.UserController;
import org.bogus.groove_auth.endpoint.user.UserInfoGetResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final UserController userController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Authorized authorized = ((HandlerMethod) handler).getMethodAnnotation(Authorized.class);
        if (authorized == null) {
            return true;
        }

        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (accessToken == null) {
            throw new AppException(ErrorType.UNAUTHORIZED_NOT_FOUND_TOKEN);
        }

        UserInfoGetResponse userInfo = userController.getSelfInfo(accessToken).getData();
        if (!userInfo.getAuthorities().containsAll(Arrays.asList(authorized.value()))) {
            throw new AppException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }

        return true;
    }
}
