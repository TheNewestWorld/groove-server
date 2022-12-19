package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;

@RequiredArgsConstructor
class OciBucketBasedObjectUriMaker implements ObjectUriMaker {
    private final String prefix;
    private final String domain;

    @Override
    public String make(AttachmentType attachmentType, String objectKey) {
        if (attachmentType == AttachmentType.PRIVATE_RECORD) {
            return String.format("%s/attachments/%s/%s", domain, attachmentType, objectKey);
        } else {
            return String.format("%s/upload/%s/%s", prefix, attachmentType.getPath(), objectKey);
        }
    }
}
