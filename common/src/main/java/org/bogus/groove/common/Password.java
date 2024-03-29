package org.bogus.groove.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Password {
    private final String value;

    @Override
    public String toString() {
        return "[PASSWORD]";
    }
}
