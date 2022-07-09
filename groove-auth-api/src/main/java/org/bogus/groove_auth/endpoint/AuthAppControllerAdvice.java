package org.bogus.groove_auth.endpoint;

import org.bogus.groove.common.CommonResponse;
import org.bogus.groove_auth.error.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthAppControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AppException.class)
    public ResponseEntity<CommonResponse<Void>> handleAppException(AppException e) {
        HttpStatus status = HttpStatus.resolve(e.getErrorType().getHttpStatus());
        switch (e.getErrorType().getLogLevel()) {
            case DEBUG:
                logger.debug("[{}] {}: {}", status.value(), status.getReasonPhrase(), e.getMessage());
                break;
            case INFO:
                logger.info("[{}] {}: {}", status.value(), status.getReasonPhrase(), e.getMessage());
                break;
            case WARN:
                logger.warn("[{}] {}: {}", status.value(), status.getReasonPhrase(), e.getMessage());
                break;
            case FATAL:
                logger.error("[{}] {}: {}", status.value(), status.getReasonPhrase(), e.getMessage());
                break;
            default:
                logger.info("[{}] {}: {}", status.value(), status.getReasonPhrase(), e.getMessage());
        }

        return ResponseEntity.status(status.value())
            .body(
                CommonResponse.error(
                    e.getErrorType().name(),
                    e.getMessage(),
                    e.getExtraData()
                )
            );
    }
}
