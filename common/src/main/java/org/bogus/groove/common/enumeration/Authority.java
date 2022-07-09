package org.bogus.groove.common.enumeration;

import java.util.List;

public enum Authority {
    USER,
    TRAINER,
    ADMIN;

    public static final List<Authority> DEFAULT = List.of(USER);
}
