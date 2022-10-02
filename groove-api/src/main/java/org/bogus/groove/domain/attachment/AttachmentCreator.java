package org.bogus.groove.domain.attachment;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.object.ObjectUploadResult;
import org.bogus.groove.storage.entity.AttachmentEntity;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentCreator {
    private final AttachmentRepository attachmentRepository;

    public Attachment create(ObjectUploadResult param) {
        return new Attachment(
            attachmentRepository.save(
                new AttachmentEntity(
                    param.getKey(),
                    param.getPath(),
                    param.getSize(),
                    param.getFileName(),
                    param.getExtension(),
                    param.getType()
                )
            )
        );
    }
}
