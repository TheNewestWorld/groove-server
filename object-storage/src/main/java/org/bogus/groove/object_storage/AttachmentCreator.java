package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.AttachmentEntity;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentCreator {
    private final AttachmentRepository attachmentRepository;
    private final ObjectUriMaker objectUriMaker;

    public Attachment create(AttachmentCreateParam param) {
        var entity = attachmentRepository.save(
            new AttachmentEntity(
                param.getObjectKey(),
                param.getPath(),
                param.getFileName(),
                param.getSize(),
                param.getResourceId(),
                param.getAttachmentType()
            )
        );
        return new Attachment(entity, objectUriMaker.make(entity.getAttachmentType(), entity.getObjectKey()));
    }
}
