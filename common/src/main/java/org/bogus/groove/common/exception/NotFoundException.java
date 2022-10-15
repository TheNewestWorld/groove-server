package org.bogus.groove.common.exception;

import java.util.Map;

public class NotFoundException extends AppException {
    public NotFoundException(ErrorType error) {
        super(error, null, null);
    }

    public NotFoundException(ErrorType error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public NotFoundException(ErrorType error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public NotFoundException(ErrorType error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
