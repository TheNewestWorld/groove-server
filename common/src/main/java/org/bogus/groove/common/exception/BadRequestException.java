package org.bogus.groove.common.exception;

import java.util.Map;

public class BadRequestException extends AppException {
    public BadRequestException(ErrorType error) {
        super(error, null, null);
    }

    public BadRequestException(ErrorType error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public BadRequestException(ErrorType error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public BadRequestException(ErrorType error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
