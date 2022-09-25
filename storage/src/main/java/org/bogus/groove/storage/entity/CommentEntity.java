package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
public class CommentEntity extends BaseEntity {

    @Column(name = "content")
    @Setter
    private String content;

    @Column(name = "deleted_flag")
    @Setter
    private boolean isDeleted;

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "ref_post_id")
    private Long postId;

    public CommentEntity(String content, Boolean isDeleted, Long userId, Long postId) {
        this.content = content;
        this.isDeleted = isDeleted;
        this.userId = userId;
        this.postId = postId;
    }
}
