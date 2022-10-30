package org.bogus.groove.object_storage;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentReader {
    private final AttachmentRepository attachmentRepository;

    // TODO 필요한 곳에서 조인으로 가져오면서 uri 도 만들어주도록 변경
    @Value("${application.domain}")
    private String domain;

    public Attachment read(String objectKey, AttachmentType attachmentType) {
        return attachmentRepository
            .findByObjectKeyAndAttachmentType(objectKey, attachmentType).map((entity) -> new Attachment(entity, domain))
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT));
    }

    public Optional<Attachment> readOrNull(Long attachmentId) {
        if (attachmentId == null) {
            return Optional.empty();
        } else {
            return attachmentRepository.findById(attachmentId).map((entity) -> new Attachment(entity, domain));
        }
    }
}
