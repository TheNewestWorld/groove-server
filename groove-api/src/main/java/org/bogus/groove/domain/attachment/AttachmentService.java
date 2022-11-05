package org.bogus.groove.domain.attachment;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.ForbiddenException;
import org.bogus.groove.object_storage.AttachmentAuthorityChecker;
import org.bogus.groove.object_storage.AttachmentDownload;
import org.bogus.groove.object_storage.AttachmentDownloader;
import org.bogus.groove.object_storage.AttachmentReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentDownloader downloader;
    private final AttachmentReader attachmentReader;
    private final AttachmentAuthorityChecker attachmentAuthorityChecker;

    public AttachmentDownload download(String objectKey, AttachmentType attachmentType, Long userId) {
        var attachment = attachmentReader.read(objectKey, attachmentType);
        var authorized = attachmentAuthorityChecker.check(attachment.getId(), AttachmentType.PRIVATE_RECORD, userId);
        if (!authorized) {
            throw new ForbiddenException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
        return downloader.download(objectKey, attachmentType);
    }
}
