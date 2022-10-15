package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "record")
@NoArgsConstructor
@Getter
public class RecordEntity extends BaseEntity {
    @Column(name = "ref_user_id")
    private long userId;

    @Column(name = "ref_attachment_id")
    private long attachmentId;

    @Setter
    @Column(name = "deleted_flag")
    private boolean isDeleted;

    public RecordEntity(long userId, long attachmentId) {
        this.userId = userId;
        this.attachmentId = attachmentId;
    }
}