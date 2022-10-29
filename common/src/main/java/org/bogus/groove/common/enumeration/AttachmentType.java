package org.bogus.groove.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttachmentType {
    PROFILE("profile"),
    PRIVATE_RECORD("private-record")
    ;

    private final String path;
}
