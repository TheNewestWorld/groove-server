package org.bogus.groove.domain.attachment;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentReader {
    private final AttachmentRepository attachmentRepository;

    public Attachment read(long attachmentId) {
        return readOrNull(attachmentId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT));
    }

    public Optional<Attachment> readOrNull(long attachmentId) {
        return attachmentRepository.findById(attachmentId).map(Attachment::new);
    }
}
