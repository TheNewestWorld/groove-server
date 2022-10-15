package org.bogus.groove.domain.attachment;

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
    private String name;
    private long size;
    private String extension;
    private AttachmentType fileType;

    public Attachment(AttachmentEntity entity) {
        id = entity.getId();
        objectKey = entity.getObjectKey();
        path = entity.getPath();
        name = entity.getName();
        size = entity.getSize();
        extension = entity.getExtension();
        fileType = entity.getAttachmentType();
    }
}
