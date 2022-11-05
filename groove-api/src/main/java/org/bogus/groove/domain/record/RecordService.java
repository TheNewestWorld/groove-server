package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.ForbiddenException;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentDeleter;
import org.bogus.groove.object_storage.AttachmentReader;
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

    public void delete(Long recordId, long userId) {
        var record = attachmentReader.read(recordId);
        if (record.getFileType() != AttachmentType.PRIVATE_RECORD || record.getResourceId() != userId) {
            throw new ForbiddenException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
        attachmentDeleter.delete(recordId);
    }
}
