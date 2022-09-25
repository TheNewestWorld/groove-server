package org.bogus.groove.endpoint.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.domain.post.Post;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
    private boolean isDeleted;
    private Long userId;
    private Long categoryId;
    private Integer commentCount;

    public PostResponse(Post post, Integer commentCount) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeCount = post.getLikeCount();
        this.isDeleted = post.isDeleted();
        this.userId = post.getUserId();
        this.categoryId = post.getCategoryId();
        this.commentCount = commentCount;
    }
}
