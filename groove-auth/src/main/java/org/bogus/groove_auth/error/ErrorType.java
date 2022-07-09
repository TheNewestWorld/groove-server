package org.bogus.groove_auth.error;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
public enum ErrorType {
    NOT_FOUND_USER(LogLevel.INFO, 404, "유저 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_NOT_FOUND_USER_TOKEN(LogLevel.INFO, 401, "인증 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_INVALID_TOKEN(LogLevel.INFO, 401, "잘못된 인증 정보입니다."),
    UNAUTHORIZED_TOKEN_EXPIRED(LogLevel.INFO, 401, "인증이 만료되었습니다."),
    UNAUTHORIZED_LOGIN_REQUEST(LogLevel.INFO, 401, "로그인이 실패했습니다.");

    private final LogLevel logLevel;
    private final int httpStatus;
    private final String defaultMessage;

    ErrorType(LogLevel logLevel, int httpStatus, String defaultMessage) {
        this.logLevel = logLevel;
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }
}
