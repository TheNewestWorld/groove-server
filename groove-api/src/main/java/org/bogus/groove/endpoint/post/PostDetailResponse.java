package org.bogus.groove.endpoint.post;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.domain.post.PostGetDetailResult;

@Getter
public class PostDetailResponse {
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private Long userId;
    private boolean likeFlag;
    private Integer likeCount;
    private Integer commentCount;

    public PostDetailResponse(PostGetDetailResult postDetail) {
        this.id = postDetail.getPost().getId();
        this.createdAt = postDetail.getCreatedAt();
        this.title = postDetail.getPost().getTitle();
        this.content = postDetail.getPost().getContent();
        this.userId = postDetail.getPost().getUserId();
        this.likeFlag = postDetail.getPost().isLikeFlag();
        this.likeCount = postDetail.getPost().getLikeCount();
        this.commentCount = postDetail.getPost().getCommentCount();
    }
}
