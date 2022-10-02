package org.bogus.groove.domain.comment;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.storage.entity.CommentEntity;

@Getter
@AllArgsConstructor
@ToString
public class Comment {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private boolean isDeleted;
    private Long parentId;
    private Long userId;
    private Long postId;

    public Comment(CommentEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.content = entity.getContent();
        this.isDeleted = entity.isDeleted();
        this.parentId = entity.getParentId();
        this.userId = entity.getUserId();
        this.postId = entity.getPostId();
    }
}
