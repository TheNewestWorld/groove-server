package org.bogus.groove.endpoint;

import org.bogus.groove.common.AppException;
import org.bogus.groove.common.BadRequestException;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.ForbiddenException;
import org.bogus.groove.common.InternalServerException;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.common.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AppException.class)
    public ResponseEntity<CommonResponse<Void>> handleAppException(AppException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

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
        } else if (e instanceof InternalServerException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("[{}] {}, {}", status.value(), status.getReasonPhrase(), e.getMessage());
        }

        return ResponseEntity.status(status.value())
            .body(
                CommonResponse.error(
                    e.getError().code(),
                    e.getMessage(),
                    e.getExtraData()
                )
            );
    }
}
