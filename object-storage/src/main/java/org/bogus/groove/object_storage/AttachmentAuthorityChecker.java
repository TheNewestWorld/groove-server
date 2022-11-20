package org.bogus.groove.object_storage;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentAuthorityChecker {
    private final AttachmentReader attachmentReader;

    public boolean check(long attachmentId, AttachmentType attachmentType, Long resourceId) {
        var record = attachmentReader.read(attachmentId);
        if (record.getFileType() != attachmentType) {
            throw new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT);
        }
        return hasAuthority(resourceId, record);
    }

    public boolean check(String objectKey, AttachmentType attachmentType, Long resourceId) {
        var record = attachmentReader.read(objectKey, attachmentType);
        return hasAuthority(resourceId, record);
    }

    private boolean hasAuthority(Long resourceId, Attachment record) {
        if (record.getFileType() == AttachmentType.PRIVATE_RECORD) {
            return record.getResourceId().equals(resourceId);
        }
        return true;
    }
}
