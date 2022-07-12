package org.bogus.groove.common;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AppException extends RuntimeException {
    @Getter
    private final AppError error;
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
