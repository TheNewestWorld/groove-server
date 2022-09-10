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

    @Column(name = "path")
    private String path;

    @Column(name = "size")
    private Integer size;

    @Column(name = "extension")
    private String extension;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;

    public AttachmentEntity(String path, Integer size, String extension, AttachmentType attachmentType) {
        this.path = path;
        this.size = size;
        this.extension = extension;
        this.attachmentType = attachmentType;
    }
}
