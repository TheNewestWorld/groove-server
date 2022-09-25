package org.bogus.groove.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.storage.entity.CommentEntity;

@Getter
@AllArgsConstructor
@ToString
public class Comment {
    private Long id;
    private String content;
    private boolean isDeleted;
    private Long userId;
    private Long postId;

    public Comment(CommentEntity entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.isDeleted = entity.isDeleted();
        this.userId = entity.getUserId();
        this.postId = entity.getPostId();
    }
}
