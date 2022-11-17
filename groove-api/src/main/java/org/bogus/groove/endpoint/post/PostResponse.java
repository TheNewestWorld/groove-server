package org.bogus.groove.endpoint.post;

import java.util.List;
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
    private String nickName;
    private String profileUri;
    private boolean likeFlag;
    private Integer likeCount;
    private Integer commentCount;
    private Long categoryId;
    private boolean authority;
    private List<String> attachmentUris;

    public PostResponse(PostGetResult post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.nickName = post.getNickName();
        this.profileUri = post.getProfileUri();
        this.likeFlag = post.isLikeFlag();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.categoryId = post.getCategoryId();
        this.authority = post.isAuthority();
        this.attachmentUris = post.getAttachmentUris();
    }
}
