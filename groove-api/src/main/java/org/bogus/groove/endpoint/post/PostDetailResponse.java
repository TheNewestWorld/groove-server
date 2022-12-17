package org.bogus.groove.endpoint.post;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.bogus.groove.domain.post.PostGetDetailResult;
import org.bogus.groove.object_storage.Attachment;

@Getter
public class PostDetailResponse {
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private Long userId;
    private String nickName;
    private String profileUri;
    private boolean likeFlag;
    private Integer likeCount;
    private Integer commentCount;
    private Long categoryId;
    private boolean authority;

    @Schema(defaultValue = "POST_IMAGE")
    private List<Attachment> attachments;

    public PostDetailResponse(PostGetDetailResult postDetail) {
        this.id = postDetail.getPost().getId();
        this.createdAt = postDetail.getCreatedAt();
        this.title = postDetail.getPost().getTitle();
        this.content = postDetail.getPost().getContent();
        this.userId = postDetail.getPost().getUserId();
        this.nickName = postDetail.getPost().getNickName();
        this.profileUri = postDetail.getPost().getProfileUri();
        this.likeFlag = postDetail.getPost().isLikeFlag();
        this.likeCount = postDetail.getPost().getLikeCount();
        this.commentCount = postDetail.getPost().getCommentCount();
        this.categoryId = postDetail.getPost().getCategoryId();
        this.authority = postDetail.getPost().isAuthority();
        this.attachments = postDetail.getPost().getAttachments();
    }
}
