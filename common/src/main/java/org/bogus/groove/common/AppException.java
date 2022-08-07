package org.bogus.groove.common;

import java.util.Map;
import lombok.Getter;

public class AppException extends RuntimeException {
    @Getter
    private final AppError error;
    private final String extraMessage;
    @Getter
    private final Map<String, Object> extraData;

    protected AppException(AppError error, String extraMessage, Map<String, Object> extraData) {
        this.error = error;
        this.extraMessage = extraMessage;
        this.extraData = extraData;
    }

    public String getMessage() {
        if (extraMessage == null) {
            return error.message();
        } else {
            return String.format("%s : %s", error.message(), extraMessage);
        }
    }
}
