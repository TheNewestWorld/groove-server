package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.ForbiddenException;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentReader;
import org.bogus.groove.object_storage.AttachmentUploader;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordCreator recordCreator;
    private final RecordReader recordReader;
    private final RecordDeleter recordDeleter;
    private final AttachmentReader attachmentReader;
    private final AttachmentUploader attachmentUploader;

    @Transactional
    public void upload(long userId, RecordUploadParam param) {
        var result = attachmentUploader.upload(param.toAttachmentUploadParam());
        recordCreator.create(userId, result.getAttachmentId());
    }

    public Slice<RecordGetResult> getAll(long userId, int page, int size) {
        var records = recordReader.read(userId, page, size);

        return new SliceImpl<>(
            records.map((record) -> {
                    var attachment = attachmentReader.readOrNull(record.getAttachmentId());
                    return new RecordGetResult(
                        attachment.map(Attachment::getUri).orElse(null),
                        attachment.map(Attachment::getFileName).orElse(null),
                        record.getCreatedAt()
                    );
                }
            ).toList(),
            records.getPageable(),
            records.hasNext()
        );
    }

    public void delete(Long recordId, long userId) {
        var record = recordReader.readById(recordId);
        if (record.getUserId() != userId) {
            throw new ForbiddenException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
        recordDeleter.delete(recordId);
    }
}
