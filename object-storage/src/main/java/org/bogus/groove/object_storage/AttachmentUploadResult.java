package org.bogus.groove.object_storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AttachmentUploadResult {
    private final long attachmentId;
    private final String fileName;
    private final String objectKey;
    private final String path;
}
