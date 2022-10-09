package org.bogus.groove.endpoint.post;

import lombok.Getter;
import org.bogus.groove.domain.post.PostGetDetailResult;

@Getter
public class PostDetailResponse {
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
    private boolean isDeleted;
    private Long userId;
    private Long categoryId;
    private Integer commentCount;

    public PostDetailResponse(PostGetDetailResult postDetail) {
        this.id = postDetail.getPost().getId();
        this.title = postDetail.getPost().getTitle();
        this.content = postDetail.getPost().getContent();
        this.likeCount = postDetail.getPost().getLikeCount();
        this.isDeleted = postDetail.getPost().isDeleted();
        this.userId = postDetail.getPost().getUserId();
        this.categoryId = postDetail.getPost().getCategoryId();
        this.commentCount = postDetail.getPost().getCommentCount();
    }
}
