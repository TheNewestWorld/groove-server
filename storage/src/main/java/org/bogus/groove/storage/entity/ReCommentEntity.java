package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "re_comment")
@Getter
@NoArgsConstructor
public class ReCommentEntity extends BaseEntity {

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ref_comment_id")
    private Long commentId;

    public ReCommentEntity(String content, Long userId, Long commentId) {
        super();
        this.content = content;
        this.userId = userId;
        this.commentId = commentId;
    }
}
