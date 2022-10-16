package org.bogus.groove.endpoint.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.domain.post.PostGetResult;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private boolean likeFlag;
    private Integer likeCount;
    private Integer commentCount;

    public PostResponse(PostGetResult post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.likeFlag = post.isLikeFlag();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
    }
}
