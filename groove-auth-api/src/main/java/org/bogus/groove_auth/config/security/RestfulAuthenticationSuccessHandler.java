package org.bogus.groove_auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove_auth.domain.user.token.TokenGenerator;
import org.bogus.groove_auth.endpoint.auth.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class RestfulAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final TokenGenerator tokenGenerator;
    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        long userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(mapper.writeValueAsString(CommonResponse.success(login(userId))));
    }

    private LoginResponse login(long userId) {
        var accessToken = tokenGenerator.generateAccessToken(userId);
        var refreshToken = tokenGenerator.generateRefreshToken(userId);

        return new LoginResponse(accessToken, refreshToken);
    }
}
