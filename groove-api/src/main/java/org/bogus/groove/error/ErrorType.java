package org.bogus.groove.error;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.AppError;

@RequiredArgsConstructor
public enum ErrorType implements AppError {
    NOT_FOUND("정보를 찾을 수 없습니다."),
    NOT_FOUND_SOMETHING("무언가의 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_NOT_FOUND_TOKEN("인증 정보를 찾을 수 없습니다."),
    FORBIDDEN_NOT_ENOUGH_AUTHORITY("권한이 없습니다.");

    private final String message;

    @Override
    public String code() {
        return this.name();
    }

    @Override
    public String message() {
        return this.message;
    }
}
