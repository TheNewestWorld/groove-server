package org.bogus.groove.common.exception;

import java.util.Map;

public class InternalServerException extends AppException {
    public InternalServerException(ErrorType error) {
        super(error, null, null, null);
    }

    public InternalServerException(ErrorType error, Throwable throwable) {
        super(error, null, null, throwable);
    }

    public InternalServerException(ErrorType error, String extraMessage) {
        super(error, extraMessage, null, null);
    }

    public InternalServerException(ErrorType error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData, null);
    }

    public InternalServerException(ErrorType error, Map<String, Object> extraData) {
        super(error, null, extraData, null);
    }
}
