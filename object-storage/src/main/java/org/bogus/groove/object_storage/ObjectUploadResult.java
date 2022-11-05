package org.bogus.groove.object_storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class ObjectUploadResult {
    private final String objectKey;
    private final String path;
}
