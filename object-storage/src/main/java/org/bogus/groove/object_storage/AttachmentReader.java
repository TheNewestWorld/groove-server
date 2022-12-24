package org.bogus.groove.object_storage;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.entity.AttachmentEntity;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentReader {
    private final AttachmentRepository attachmentRepository;
    private final ObjectUriMaker objectUriMaker;

    public List<Attachment> readAll(Long resourceId, AttachmentType attachmentType) {
        return attachmentRepository
            .findAllByResourceIdAndAttachmentTypeAndIsDeletedIsFalse(resourceId, attachmentType)
            .stream().map(attachmentMapper())
            .collect(Collectors.toList());
    }

    public Slice<Attachment> readAll(Long resourceId, AttachmentType attachmentType, Pageable pageable) {
        var slice = attachmentRepository
            .findAllByResourceIdAndAttachmentTypeAndIsDeletedIsFalse(resourceId, attachmentType, pageable);

        return slice.map(attachmentMapper());
    }

    public Attachment read(String objectKey, AttachmentType attachmentType) {
        return attachmentRepository
            .findByObjectKeyAndAttachmentTypeAndIsDeletedIsFalse(objectKey, attachmentType).map(attachmentMapper())
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT));
    }

    public Attachment read(Long attachmentId) {
        return attachmentRepository.findById(attachmentId).map(attachmentMapper())
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT));
    }

    private Function<AttachmentEntity, Attachment> attachmentMapper() {
        return (entity) -> new Attachment(entity, objectUriMaker.make(entity.getAttachmentType(), entity.getObjectKey()));
    }
}
