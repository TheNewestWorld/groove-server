package org.bogus.groove.common;

import java.util.Map;

public class InternalServerException extends AppException {
    public InternalServerException(AppError error) {
        super(error, null, null);
    }

    public InternalServerException(AppError error, String extraMessage) {
        super(error, extraMessage, null);
    }

    public InternalServerException(AppError error, String extraMessage, Map<String, Object> extraData) {
        super(error, extraMessage, extraData);
    }

    public InternalServerException(AppError error, Map<String, Object> extraData) {
        super(error, null, extraData);
    }
}
