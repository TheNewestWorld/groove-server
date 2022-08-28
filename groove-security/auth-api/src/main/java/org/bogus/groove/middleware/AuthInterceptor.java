//package org.bogus.groove.endpoint.middleware;
//
//import java.util.Arrays;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.bogus.groove.client.auth.AuthClient;
//import org.bogus.groove.domain.user.UserInfo;
//import org.bogus.groove.common.ForbiddenException;
//import org.bogus.groove.common.UnauthorizedException;
//import org.bogus.groove.common.ErrorType;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//@RequiredArgsConstructor
//public class AuthInterceptor implements HandlerInterceptor {
//    @Lazy
//    private final AuthClient authClient;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        Authorized authorized = ((HandlerMethod) handler).getMethodAnnotation(Authorized.class);
//        if (authorized == null) {
//            return true;
//        }
//
//        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (accessToken == null) {
//            throw new UnauthorizedException(ErrorType.UNAUTHORIZED_NOT_FOUND_TOKEN);
//        }
//
//        UserInfo userInfo = authClient.getUserInfoByToken(accessToken);
//        if (!userInfo.getAuthorities().containsAll(Arrays.asList(authorized.value()))) {
//            throw new ForbiddenException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
//        }
//
//        return true;
//    }
//}
