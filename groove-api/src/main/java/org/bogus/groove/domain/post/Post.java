package org.bogus.groove.domain.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.storage.entity.PostEntity;

@Getter
@AllArgsConstructor
@ToString
public class Post {
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
    private boolean isDeleted;
    private Long userId;
    private Long categoryId;

    public Post(PostEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.likeCount = entity.getLikeCount();
        this.isDeleted = entity.isDeleted();
        this.userId = entity.getUserId();
        this.categoryId = entity.getCategoryId();
    }
}
