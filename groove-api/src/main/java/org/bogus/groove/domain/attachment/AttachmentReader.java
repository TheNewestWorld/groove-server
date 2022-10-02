package org.bogus.groove.domain.attachment;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.AttachmentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentReader {
    private final AttachmentRepository attachmentRepository;

    public Optional<Attachment> readOrNull(long attachmentId) {
        return attachmentRepository.findById(attachmentId).map(Attachment::new);
    }
}
