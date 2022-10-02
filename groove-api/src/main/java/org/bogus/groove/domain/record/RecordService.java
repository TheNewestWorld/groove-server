package org.bogus.groove.domain.record;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.domain.attachment.AttachmentCreator;
import org.bogus.groove.domain.object.ObjectStorage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final ObjectStorage objectStorage;
    private final AttachmentCreator attachmentCreator;
    private final RecordCreator recordCreator;

    @Transactional
    public void upload(long userId, MultipartFile record) throws IOException {
        var uploadResult = objectStorage.upload(record, AttachmentType.VOICE);
        var attachment = attachmentCreator.create(uploadResult);

        recordCreator.create(userId, attachment.getId());
    }

    public void getAll(long userId) {

    }
}
