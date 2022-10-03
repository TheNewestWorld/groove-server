package org.bogus.groove.common.exception;

import java.util.Map;

public class InternalServerException extends AppException {
    public InternalServerException(ErrorType error) {
        super(error, null, null);
    }

    public InternalServerException(ErrorType error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public InternalServerException(ErrorType error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public InternalServerException(ErrorType error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
