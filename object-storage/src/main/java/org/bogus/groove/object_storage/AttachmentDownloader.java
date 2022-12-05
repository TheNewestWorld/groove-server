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
        var attachment = attachmentReader.read(objectKey, attachmentType);
        var input = objectStorage.download(objectKey, attachmentType);

        return new AttachmentDownload(
            input,
            attachment.getFileName()
        );
    }
}
