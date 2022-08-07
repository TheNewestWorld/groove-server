package org.bogus.groove_auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bogus.groove.common.AppException;
import org.bogus.groove.common.BadRequestException;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.ForbiddenException;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.common.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class SecurityFilterExceptionHandler implements AuthenticationFailureHandler, AccessDeniedHandler {
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SecurityFilterExceptionHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException {
        handleException(response, exception);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException {
        handleException(response, accessDeniedException);
    }

    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        HttpStatus status;
        if (e instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else if (e instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else if (e instanceof UnauthorizedException) {
            status = HttpStatus.UNAUTHORIZED;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else if (e instanceof ForbiddenException) {
            status = HttpStatus.FORBIDDEN;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        }

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(mapper.writeValueAsString(makeCommonResponse(e)));
    }

    private CommonResponse<?> makeCommonResponse(Exception e) {
        if (e instanceof AppException) {
            return CommonResponse.error(
                ((AppException) e).getError().code(),
                e.getMessage()
            );
        } else {
            return CommonResponse.error(
                e.getClass().getSimpleName(),
                e.getMessage()
            );
        }
    }
}
