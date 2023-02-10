package org.bogus.groove.common.exception;

import java.util.Map;

public class ForbiddenException extends AppException {
    public ForbiddenException(ErrorType error) {
        super(error, null, null, null);
    }

    public ForbiddenException(ErrorType error, String extraMessage) {
        super(error, extraMessage, null, null);
    }

    public ForbiddenException(ErrorType error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData, null);
    }

    public ForbiddenException(ErrorType error, Map<String, Object> extraData) {
        super(error, null, extraData, null);
    }
}
