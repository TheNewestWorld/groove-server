package org.bogus.groove.common;

import java.util.Map;

public class ForbiddenException extends AppException {
    public ForbiddenException(ErrorType error) {
        super(error, null, null);
    }

    public ForbiddenException(ErrorType error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public ForbiddenException(ErrorType error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public ForbiddenException(ErrorType error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
