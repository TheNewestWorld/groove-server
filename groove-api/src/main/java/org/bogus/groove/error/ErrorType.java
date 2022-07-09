package org.bogus.groove.error;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
    NOT_FOUND(LogLevel.INFO, HttpStatus.NOT_FOUND, "정보를 찾을 수 없습니다."),
    NOT_FOUND_SOMETHING(LogLevel.INFO, HttpStatus.NOT_FOUND, "무언가의 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_NOT_FOUND_TOKEN(LogLevel.INFO, HttpStatus.UNAUTHORIZED, "인증 정보를 찾을 수 없습니다."),
    FORBIDDEN_NOT_ENOUGH_AUTHORITY(LogLevel.INFO, HttpStatus.FORBIDDEN, "권한이 없습니다.");

    private final LogLevel logLevel;
    private final HttpStatus status;
    private final String defaultMessage;

    ErrorType(LogLevel logLevel, HttpStatus status, String defaultMessage) {
        this.logLevel = logLevel;
        this.status = status;
        this.defaultMessage = defaultMessage;
    }
}
