package org.bogus.groove.object_storage;

import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;

@RequiredArgsConstructor
class OciBucketBasedObjectUriMaker implements ObjectUriMaker {
    private final String preAuthorizedUrl;
    private final String domain;

    // TODO download path, upload path 분리하고, 이 클래스로 로직 모아야 함
    @Override
    public String make(AttachmentType attachmentType, String objectKey) {
        if (attachmentType.isPreAuthorized()) {
            return Path.of(preAuthorizedUrl, "/upload/public", attachmentType.getPath(), objectKey).toString();
        } else {
            return Path.of(domain, "attachments", attachmentType.name(), objectKey).toString();
        }
    }
}
