package org.bogus.groove.client.auth;

import java.util.Arrays;

public enum UserType {
    GROOVE,
    KAKAO,
    NAVER,
    GOOGLE
    ;

    public static UserType ofOrNull(String name) {
        return Arrays.stream(values())
            .filter((type) -> type.name().equals(name))
            .findFirst()
            .orElse(null);
    }
}
