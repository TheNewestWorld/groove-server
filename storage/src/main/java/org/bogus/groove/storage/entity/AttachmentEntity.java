package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;

@Entity
@Table(name = "attachment")
@Getter
@NoArgsConstructor
public class AttachmentEntity extends BaseEntity {
    @Column(name = "object_key")
    private String objectKey;

    @Column(name = "path")
    private String path;

    @Column(name = "size")
    private long size;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;

    public AttachmentEntity(String objectKey, String path, long size, String name, String extension, AttachmentType attachmentType) {
        this.objectKey = objectKey;
        this.path = path;
        this.size = size;
        this.name = name;
        this.extension = extension;
        this.attachmentType = attachmentType;
    }
}
