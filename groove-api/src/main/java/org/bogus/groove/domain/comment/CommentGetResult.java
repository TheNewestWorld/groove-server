package org.bogus.groove.domain.comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class CommentGetResult {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private boolean isDeleted;
    private Long parentId;
    private Long userId;
    private Long postId;
    private List<CommentGetResult> reComments;

    public CommentGetResult(Comment comment) {
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.content = comment.getContent();
        this.isDeleted = comment.isDeleted();
        this.parentId = comment.getParentId();
        this.userId = comment.getUserId();
        this.postId = comment.getPostId();
    }

    public CommentGetResult(Comment comment, List<Comment> reComments) {
        this(comment);
        this.reComments = reComments.stream().map(reComment -> new CommentGetResult(reComment)).collect(Collectors.toList());
    }
}
