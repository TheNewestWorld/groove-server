package org.bogus.groove.domain.comment;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class CommentGetResult {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private boolean isDeleted;
    private Long parentId;
    private Long userId;
    private String nickName;
    private String profileUri;
    private Long postId;
    private boolean authority;
    private List<CommentGetResult> reComments;

    public CommentGetResult(Comment comment, String nickName, String profileUri, boolean authority) {
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.content = comment.getContent();
        this.isDeleted = comment.isDeleted();
        this.parentId = comment.getParentId();
        this.userId = comment.getUserId();
        this.nickName = nickName;
        this.profileUri = profileUri;
        this.postId = comment.getPostId();
        this.authority = authority;
    }

    public CommentGetResult(Comment comment, String nickName, String profileUri, boolean authority, List<CommentGetResult> reComments) {
        this(comment, nickName, profileUri, authority);
        this.nickName = nickName;
        this.reComments = reComments;
    }
}
