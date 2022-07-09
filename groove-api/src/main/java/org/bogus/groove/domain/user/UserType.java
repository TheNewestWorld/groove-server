package org.bogus.groove.domain.user;

public enum UserType {
    GROOVE,
    KAKAO,
    NAVER,
    GOOGLE;

    public static final UserType DEFAULT = GROOVE;
}
