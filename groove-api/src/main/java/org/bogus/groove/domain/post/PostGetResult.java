package org.bogus.groove.domain.post;

import java.util.List;
import lombok.Getter;
import org.bogus.groove.object_storage.Attachment;

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
    private boolean authority;
    private List<Attachment> attachments;

    public PostGetResult(Post post, String nickName, String profileUri, boolean likeFlag, boolean authority, List<Attachment> attachments) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.nickName = nickName;
        this.profileUri = profileUri;
        this.likeFlag = likeFlag;
        this.authority = authority;
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.categoryId = post.getCategoryId();
        this.attachments = attachments;
    }
}
