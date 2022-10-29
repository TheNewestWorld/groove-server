package org.bogus.groove.domain.post;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.storage.entity.PostEntity;

@Getter
@AllArgsConstructor
@ToString
public class Post {
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private boolean isDeleted;
    private Integer likeCount;
    private Integer commentCount;
    private Long userId;

    public Post(PostEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.isDeleted = entity.isDeleted();
        this.likeCount = entity.getLikeCount();
        this.commentCount = entity.getCommentCount();
        this.userId = entity.getUserId();
    }
}
