package org.bogus.groove.endpoint;

import org.bogus.groove.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice(basePackages = { "org.bogus.groove.endpoint" })
public class SystemControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CommonResponse<Void>> handleRequestStatusException(ResponseStatusException e) {
        logger.error("[{}] {}: {}", e.getStatus().value(), e.getReason(), e.getMessage());
        return ResponseEntity.status(e.getStatus())
            .body(CommonResponse.error(e.getReason(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        logger.error("{} : {}", e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(CommonResponse.error(e.getClass().getSimpleName(), e.getMessage()));
    }
}
