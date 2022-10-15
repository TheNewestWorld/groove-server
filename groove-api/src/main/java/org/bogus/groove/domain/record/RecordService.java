package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.domain.attachment.Attachment;
import org.bogus.groove.domain.attachment.AttachmentCreator;
import org.bogus.groove.domain.attachment.AttachmentReader;
import org.bogus.groove.domain.object.ObjectStorage;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final ObjectStorage objectStorage;
    private final AttachmentCreator attachmentCreator;
    private final RecordCreator recordCreator;
    private final RecordReader recordReader;
    private final AttachmentReader attachmentReader;

    @Transactional
    public void upload(long userId, MultipartFile record) {
        var uploadResult = objectStorage.upload(record, AttachmentType.VOICE);
        var attachment = attachmentCreator.create(uploadResult);

        recordCreator.create(userId, attachment.getId());
    }

    public Slice<RecordGetResult> getAll(long userId, int page, int size) {
        var records = recordReader.read(userId, page, size);

        return new SliceImpl<>(
            records.map((record) ->
                new RecordGetResult(
                    record.getId(),
                    findName(record),
                    record.getCreatedAt()
                )
            ).toList(),
            records.getPageable(),
            records.hasNext()
        );
    }

    private String findName(Record record) {
        return attachmentReader.readOrNull(record.getAttachmentId()).map(Attachment::getName).orElse(null);
    }

    public FileDownload download(int recordId) {
        var record = recordReader.read(recordId);
        var attachment = attachmentReader.read(record.getAttachmentId());

        return new FileDownload(
            objectStorage.download(attachment.getPath()),
            attachment.getName()
        );
    }
}
