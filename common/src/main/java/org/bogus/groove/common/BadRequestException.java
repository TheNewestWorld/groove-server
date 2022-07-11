package org.bogus.groove.common;

import java.util.Map;

public class BadRequestException extends AppException {
    public BadRequestException(AppError error) {
        super(error, null, null);
    }

    public BadRequestException(AppError error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public BadRequestException(AppError error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public BadRequestException(AppError error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
