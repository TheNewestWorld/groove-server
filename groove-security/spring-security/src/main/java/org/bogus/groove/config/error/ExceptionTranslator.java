package org.bogus.groove.config.error;

import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.exception.AppException;
import org.bogus.groove.common.exception.BadRequestException;
import org.bogus.groove.common.exception.ForbiddenException;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionTranslator {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public HttpStatus translateToHttpStatus(Throwable e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else if (e instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else if (e instanceof UnauthorizedException || e instanceof AuthenticationException) {
            status = HttpStatus.UNAUTHORIZED;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else if (e instanceof ForbiddenException || e instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
            logger.info("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        } else if (e instanceof InternalServerException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        }

        return status;
    }

    public CommonResponse<Void> translateToCommonResponse(Throwable e) {
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
