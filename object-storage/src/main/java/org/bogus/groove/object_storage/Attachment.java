package org.bogus.groove.object_storage;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.storage.entity.AttachmentEntity;

@Getter
@AllArgsConstructor
@ToString
public class Attachment {
    private Long id;
    private String objectKey;
    private String path;
    private String fileName;
    private long size;
    private Long resourceId;
    private AttachmentType fileType;
    private String uri;
    private LocalDateTime createdAt;

    public Attachment(AttachmentEntity entity, String domain) {
        this.id = entity.getId();
        this.objectKey = entity.getObjectKey();
        this.path = entity.getPath();
        this.fileName = entity.getFileName();
        this.size = entity.getSize();
        this.fileType = entity.getAttachmentType();
        this.uri = String.format("%s/attachments/%s/%s", domain, entity.getAttachmentType().name(), objectKey);
        this.createdAt = entity.getCreatedAt();
    }
}
