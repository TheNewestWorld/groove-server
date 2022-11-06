package org.bogus.groove.domain.post;

import java.util.List;
import lombok.Getter;

@Getter
public class PostGetResult {
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
    private List<String> attachmentUris;

    public PostGetResult(Post post, String nickName, String profileUri, boolean likeFlag, List<String> attachmentUris) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.nickName = nickName;
        this.profileUri = profileUri;
        this.likeFlag = likeFlag;
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.categoryId = post.getCategoryId();
        this.attachmentUris = attachmentUris;
    }
}
