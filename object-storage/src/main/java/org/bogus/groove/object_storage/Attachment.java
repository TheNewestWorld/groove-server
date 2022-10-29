package org.bogus.groove.object_storage;

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
    private AttachmentType fileType;
    private String uri;

    public Attachment(AttachmentEntity entity, String domain) {
        id = entity.getId();
        objectKey = entity.getObjectKey();
        path = entity.getPath();
        fileName = entity.getFileName();
        size = entity.getSize();
        fileType = entity.getAttachmentType();
        uri = String.format("%s/attachments/%s/%s", domain, entity.getAttachmentType().name(), objectKey);
    }
}