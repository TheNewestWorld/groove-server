package org.bogus.groove_auth.endpoint;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove_auth.endpoint.support.ExceptionTranslator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "org.bogus.groove_auth")
@RequiredArgsConstructor
public class AuthAppControllerAdvice {
    private final ExceptionTranslator exceptionTranslator;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        return ResponseEntity
            .status(exceptionTranslator.translateToHttpStatus(e))
            .body(exceptionTranslator.translateToCommonResponse(e));
    }
}
