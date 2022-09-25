package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "re_comment")
@Getter
@NoArgsConstructor
public class ReCommentEntity extends BaseEntity {

    @Column(name = "content")
    @Setter
    private String content;

    @Column(name = "deleted_flag")
    @Setter
    private boolean isDeleted;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ref_comment_id")
    private Long commentId;

    public ReCommentEntity(String content, Boolean isDeleted, Long userId, Long commentId) {
        super();
        this.content = content;
        this.isDeleted = isDeleted;
        this.userId = userId;
        this.commentId = commentId;
    }
}
