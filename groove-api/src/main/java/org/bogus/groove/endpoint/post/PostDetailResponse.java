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
    private String nickName;
    private String profieUri;
    private boolean likeFlag;
    private Integer likeCount;
    private Integer commentCount;
    private Long categoryId;

    public PostDetailResponse(PostGetDetailResult postDetail) {
        this.id = postDetail.getPost().getId();
        this.createdAt = postDetail.getCreatedAt();
        this.title = postDetail.getPost().getTitle();
        this.content = postDetail.getPost().getContent();
        this.userId = postDetail.getPost().getUserId();
        this.nickName = postDetail.getPost().getNickName();
        this.profieUri = postDetail.getPost().getProfileUri();
        this.likeFlag = postDetail.getPost().isLikeFlag();
        this.likeCount = postDetail.getPost().getLikeCount();
        this.commentCount = postDetail.getPost().getCommentCount();
        this.categoryId = postDetail.getPost().getCategoryId();
    }
}
