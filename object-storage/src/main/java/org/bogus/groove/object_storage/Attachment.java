package org.bogus.groove.object_storage;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.storage.entity.AttachmentEntity;

@Getter
public class Attachment {
    private final Long id;
    private final String objectKey;
    private final String path;
    private final String fileName;
    private final long size;
    private final Long resourceId;
    private final AttachmentType fileType;
    private final String uri;
    private final LocalDateTime createdAt;

    public Attachment(AttachmentEntity entity, String uri) {
        this.id = entity.getId();
        this.objectKey = entity.getObjectKey();
        this.path = entity.getPath();
        this.fileName = entity.getFileName();
        this.size = entity.getSize();
        this.resourceId = entity.getResourceId();
        this.fileType = entity.getAttachmentType();
        this.uri = uri;
        this.createdAt = entity.getCreatedAt();
    }
}
