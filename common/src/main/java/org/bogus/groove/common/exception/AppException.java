package org.bogus.groove.common.exception;

import java.util.Map;
import lombok.Getter;

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

    protected AppException(ErrorType error, String extraMessage, Map<String, Object> extraData, Throwable throwable) {
        super(throwable);
        this.error = error;
        this.extraMessage = extraMessage;
        this.extraData = extraData;
    }
}
