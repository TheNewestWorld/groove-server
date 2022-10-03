package org.bogus.groove.common.exception;

import java.util.Map;

public class UnauthorizedException extends AppException {
    public UnauthorizedException(ErrorType error) {
        super(error, null, null);
    }

    public UnauthorizedException(ErrorType error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public UnauthorizedException(ErrorType error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public UnauthorizedException(ErrorType error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
