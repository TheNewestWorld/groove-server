package org.bogus.groove.common;

import java.util.Map;

public class NotFoundException extends AppException {
    public NotFoundException(AppError error) {
        super(error, null, null);
    }

    public NotFoundException(AppError error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public NotFoundException(AppError error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public NotFoundException(AppError error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
