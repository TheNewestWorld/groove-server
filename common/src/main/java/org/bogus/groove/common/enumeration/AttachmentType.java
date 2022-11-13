package org.bogus.groove.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttachmentType {
    PROFILE("profile"),
    PRIVATE_RECORD("private-record"),
    POST("post")
    ;

    private final String path;
}
