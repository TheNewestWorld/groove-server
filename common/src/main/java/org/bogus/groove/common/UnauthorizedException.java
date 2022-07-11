package org.bogus.groove.common;

import java.util.Map;

public class UnauthorizedException extends AppException {
    public UnauthorizedException(AppError error) {
        super(error, null, null);
    }

    public UnauthorizedException(AppError error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public UnauthorizedException(AppError error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public UnauthorizedException(AppError error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
