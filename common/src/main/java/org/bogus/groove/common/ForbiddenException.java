package org.bogus.groove.common;

import java.util.Map;

public class ForbiddenException extends AppException {
    public ForbiddenException(AppError error) {
        super(error, null, null);
    }

    public ForbiddenException(AppError error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public ForbiddenException(AppError error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public ForbiddenException(AppError error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
