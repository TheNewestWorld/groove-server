package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentUploader {
    private final AttachmentCreator attachmentCreator;
    private final ObjectStorage objectStorage;

    public AttachmentUploadResult upload(AttachmentUploadParam param) {
        var result = objectStorage.upload(param.getInputStream(), param.getAttachmentType());
        var attachment = attachmentCreator.create(
            new AttachmentCreateParam(
                result.getObjectKey(),
                result.getPath(),
                param.getFileName(),
                param.getSize(),
                param.getResourceId(),
                param.getAttachmentType()
            )
        );
        return new AttachmentUploadResult(
            attachment.getId(),
            attachment.getFileName(),
            attachment.getObjectKey(),
            attachment.getPath()
        );
    }
}
