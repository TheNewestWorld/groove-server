package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;

@RequiredArgsConstructor
class FileSystemBasedObjectUriMaker implements ObjectUriMaker {
    private final String domain;

    @Override
    public String make(AttachmentType attachmentType, String objectKey) {
        return buildUri(domain, "attachments", attachmentType.name(), objectKey);
    }
}

