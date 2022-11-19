package org.bogus.groove.common.exception;

import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AppException extends RuntimeException {
    @Getter
    private final ErrorType error;
    private final String extraMessage;
    @Getter
    private final Map<String, Object> extraData;

    public String getMessage() {
        if (extraMessage == null) {
            return error.message();
        } else {
            return String.format("%s : %s", error.message(), extraMessage);
        }
    }
}
