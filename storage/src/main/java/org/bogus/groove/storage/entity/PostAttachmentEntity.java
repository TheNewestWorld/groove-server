package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_file")
@Getter
@NoArgsConstructor
public class PostAttachmentEntity extends BaseEntity {

    @Column(name = "ref_post_id")
    private Long postId;

    @Column(name = "ref_attachment_id")
    private Long attachmentId;

    public PostAttachmentEntity(Long postId, Long attachmentId) {
        this.postId = postId;
        this.attachmentId = attachmentId;
    }
}
