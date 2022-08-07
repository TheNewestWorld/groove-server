package org.bogus.groove_auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove_auth.endpoint.auth.LoginRequest;
import org.bogus.groove_auth.error.ErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RestfulAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper mapper;

    public RestfulAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                                       ObjectMapper mapper) {
        super(requiresAuthenticationRequestMatcher);
        this.mapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException {
        if (!isRestFulRequest(request)) {
            throw new UnauthorizedException(ErrorType.UNAUTHORIZED_LOGIN_REQUEST);
        }

        var loginRequest = tryReadLoginRequest(request);
        var notAuthenticatedToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        return getAuthenticationManager().authenticate(notAuthenticatedToken);
    }

    private boolean isRestFulRequest(HttpServletRequest request) {
        var contentTypeValue = request.getHeader(HttpHeaders.CONTENT_TYPE);
        return contentTypeValue != null && contentTypeValue.contains(MediaType.APPLICATION_JSON_VALUE);
    }

    private LoginRequest tryReadLoginRequest(HttpServletRequest request) throws IOException {
        var loginRequest = mapper.readValue(request.getReader(), LoginRequest.class);
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new UnauthorizedException(ErrorType.UNAUTHORIZED_LOGIN_REQUEST);
        }
        return loginRequest;
    }
}
