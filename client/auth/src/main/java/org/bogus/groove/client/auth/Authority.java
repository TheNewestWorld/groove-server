package org.bogus.groove.client.auth;

import java.util.Arrays;

public enum Authority {
    USER,
    TRAINER,
    ADMIN
    ;

    public static Authority ofOrNull(String name) {
        return Arrays.stream(values())
            .filter((type) -> type.name().equals(name))
            .findFirst()
            .orElse(null);
    }
}
