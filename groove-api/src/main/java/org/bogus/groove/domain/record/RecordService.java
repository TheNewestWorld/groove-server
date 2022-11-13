package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.ForbiddenException;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentAuthorityChecker;
import org.bogus.groove.object_storage.AttachmentDeleter;
import org.bogus.groove.object_storage.AttachmentReader;
import org.bogus.groove.object_storage.AttachmentUpdater;
import org.bogus.groove.object_storage.AttachmentUploadParam;
import org.bogus.groove.object_storage.AttachmentUploader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final AttachmentReader attachmentReader;
    private final AttachmentUploader attachmentUploader;
    private final AttachmentDeleter attachmentDeleter;
    private final AttachmentUpdater attachmentUpdater;
    private final AttachmentAuthorityChecker attachmentAuthorityChecker;

    @Transactional
    public void upload(long userId, RecordUploadParam param) {
        attachmentUploader.upload(
            new AttachmentUploadParam(
                param.getInputStream(),
                param.getFileName(),
                param.getSize(),
                userId,
                AttachmentType.PRIVATE_RECORD
            )
        );
    }

    public Slice<Attachment> getAll(long userId, int page, int size) {
        return attachmentReader.readAll(userId, AttachmentType.PRIVATE_RECORD, PageRequest.of(page, size));
    }

    public void delete(long recordId, long userId) {
        var authorized = attachmentAuthorityChecker.check(recordId, AttachmentType.PRIVATE_RECORD, userId);
        if (!authorized) {
            throw new ForbiddenException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
        attachmentDeleter.delete(recordId);
    }

    public void update(long recordId, long userId, String recordName) {
        var authorized = attachmentAuthorityChecker.check(recordId, AttachmentType.PRIVATE_RECORD, userId);
        if (!authorized) {
            throw new ForbiddenException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
        attachmentUpdater.updateName(recordId, recordName);
    }
}
