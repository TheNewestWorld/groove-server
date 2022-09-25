package org.bogus.groove.domain.recomment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.storage.entity.ReCommentEntity;

@Getter
@AllArgsConstructor
@ToString
public class ReComment {
    private Long id;
    private String content;
    private boolean isDeleted;
    private Long userId;
    private Long commentId;

    public ReComment(ReCommentEntity entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.isDeleted = entity.isDeleted();
        this.userId = entity.getUserId();
        this.commentId = entity.getCommentId();
    }
}
