package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.AttachmentEntity;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentUploader {
    private final AttachmentRepository attachmentRepository;

    public AttachmentUploadResult upload(AttachmentUploadParam param) {
        var result = ObjectStorage.upload(param.getInputStream(), param.getAttachmentType());
        var entity = attachmentRepository.save(
            new AttachmentEntity(
                result.getObjectKey(),
                result.getPath(),
                param.getFileName(),
                param.getSize(),
                param.getAttachmentType()
            )
        );
        return new AttachmentUploadResult(
            entity.getId(),
            entity.getFileName(),
            entity.getObjectKey(),
            entity.getPath()
        );
    }
}
