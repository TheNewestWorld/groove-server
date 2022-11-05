package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AttachmentDeleter {
    private final AttachmentRepository attachmentRepository;

    @Transactional
    public void delete(Long attachmentId) {
        var entity = attachmentRepository.findById(attachmentId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT));
        entity.setDeleted(true);
    }
}
