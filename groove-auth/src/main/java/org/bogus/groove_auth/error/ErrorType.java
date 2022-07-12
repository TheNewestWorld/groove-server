package org.bogus.groove_auth.error;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.AppError;

@RequiredArgsConstructor
public enum ErrorType implements AppError {
    NOT_FOUND_USER("유저 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_NOT_FOUND_USER_TOKEN("인증 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_INVALID_TOKEN("잘못된 인증 정보입니다."),
    UNAUTHORIZED_TOKEN_EXPIRED("인증이 만료되었습니다."),
    UNAUTHORIZED_LOGIN_REQUEST("로그인이 실패했습니다.");

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
