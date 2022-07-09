package org.bogus.groove.error;

import java.util.Map;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final ErrorType errorType;
    private final Map<String, Object> extraData;

    public AppException(ErrorType errorType) {
        super(errorType.getDefaultMessage(), null);
        this.errorType = errorType;
        this.extraData = null;
    }

    public AppException(ErrorType errorType, String extraMessage) {
        super(getDefaultMessage(errorType, extraMessage), null);
        this.errorType = errorType;
        this.extraData = null;
    }

    public AppException(ErrorType errorType, String extraMessage, Map<String, Object> extraData) {
        super(getDefaultMessage(errorType, extraMessage), null);
        this.errorType = errorType;
        this.extraData = extraData;
    }

    public AppException(ErrorType errorType, Map<String, Object> extraData) {
        super(errorType.getDefaultMessage(), null);
        this.errorType = errorType;
        this.extraData = extraData;
    }

    private static String getDefaultMessage(ErrorType errorType, String extraMessage) {
        return extraMessage == null ? errorType.getDefaultMessage() : String.format("%s : %s", errorType.getDefaultMessage(), extraMessage);
    }
}
