package org.bogus.groove.domain.record;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.RecordEntity;

@Getter
@RequiredArgsConstructor
public class Record {
    private final long id;
    private final long userId;
    private final long attachmentId;
    private final LocalDateTime createdAt;

    public Record(RecordEntity entity) {
        id = entity.getId();
        userId = entity.getUserId();
        attachmentId = entity.getAttachmentId();
        createdAt = entity.getCreatedAt();
    }
}
