package org.bogus.groove_auth.domain.user;

public enum UserType {
    GROOVE,
    KAKAO,
    NAVER,
    GOOGLE;

    public static final UserType DEFAULT = GROOVE;
}
