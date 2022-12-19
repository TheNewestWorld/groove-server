package org.bogus.groove.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttachmentType {
    PROFILE("profile", true),
    PRIVATE_RECORD("private-record", false),
    POST_IMAGE("post-image", true),
    POST_RECORD("post-record", true)
    ;

    private final String path;
    private final boolean preAuthorized;
}
