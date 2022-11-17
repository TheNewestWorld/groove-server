package org.bogus.groove.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorType {
    NOT_FOUND("정보를 찾을 수 없습니다."),
    NOT_FOUND_USER("유저 정보를 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY("해당 카테고리를 찾을 수 없습니다."),
    NOT_FOUND_POST("해당 게시글을 찾을 수 없습니다."),
    NOT_FOUND_COMMENT("해당 댓글을 찾을 수 없습니다."),
    NOT_FOUND_RECOMMENT("해당 대댓글을 찾을 수 없습니다."),
    NOT_FOUND_SESSION("세션을 찾을 수 없습니다."),
    NOT_FOUND_RECORD("녹음을 찾을 수 없습니다."),
    NOT_FOUND_ATTACHMENT("파일을 찾을 수 없습니다."),
    NOT_FOUND_LIKE("해당 좋아요를 찾을 수 없습니다."),
    FAILED_TO_CREATE_CATEGORY("카테고리 생성을 실패하였습니다."),
    NOT_FOUND_AUTHENTICATION_SESSION("인증 세션을 찾을 수 없습니다."),
    AUTHENTICATION_SESSION_EXPIRED("만료된 인증 세션입니다."),
    FAILED_TO_CREATE_POST("게시글 생성을 실패하였습니다."),
    FAILED_TO_CREATE_COMMENT("댓글 생성을 실패하였습니다."),
    FAILED_TO_SEND_MAIL("메일 발송에 실패했습니다."),
    FAILED_TO_LIKE_POST("게시글 좋아요를 실패하였습니다."),
    ALREADY_LIKE_POST("이미 좋아요를 한 게시글 입니다."),
    UNAUTHORIZED_NOT_FOUND_USER_TOKEN("인증 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_INVALID_TOKEN("잘못된 인증 정보입니다."),
    UNAUTHORIZED_TOKEN_EXPIRED("인증이 만료되었습니다."),
    UNAUTHORIZED_LOGIN_REQUEST("로그인이 실패했습니다."),
    FORBIDDEN_NOT_ENOUGH_AUTHORITY("권한이 없습니다."),
    DUPLICATED_USER("이미 가입된 사용자입니다."),
    DUPLICATED_NICKNAME("이미 등록된 닉네임이에요.");

    private final String message;

    public String code() {
        return this.name();
    }

    public String message() {
        return this.message;
    }
}
