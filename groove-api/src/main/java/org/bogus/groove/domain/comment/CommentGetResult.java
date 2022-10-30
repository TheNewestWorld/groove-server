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
    private Long postId;
    private List<CommentGetResult> reComments;

    public CommentGetResult(Comment comment, String nickName) {
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.content = comment.getContent();
        this.isDeleted = comment.isDeleted();
        this.parentId = comment.getParentId();
        this.userId = comment.getUserId();
        this.nickName = nickName;
        this.postId = comment.getPostId();
    }

    public CommentGetResult(Comment comment, String nickName, List<CommentGetResult> reComments) {
        this(comment, nickName);
        this.nickName = nickName;
        this.reComments = reComments;
    }
}
