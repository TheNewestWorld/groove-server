package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentDownloader {
    private final AttachmentReader attachmentReader;
    private final ObjectStorage objectStorage;

    public AttachmentDownload download(String objectKey, AttachmentType attachmentType) {
        var input = objectStorage.download(objectKey, attachmentType);

        return new AttachmentDownload(
            input,
            getFileName(attachmentType, objectKey)
        );
    }

    private String getFileName(AttachmentType attachmentType, String objectKey) {
        if (attachmentType.equals(AttachmentType.MISCELLANEOUS)) {
            return objectKey;
        } else {
            var attachment = attachmentReader.read(objectKey, attachmentType);
            return attachment.getFileName();
        }
    }
}
