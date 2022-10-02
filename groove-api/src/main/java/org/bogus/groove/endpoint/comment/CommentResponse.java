package org.bogus.groove.endpoint.comment;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.domain.comment.Comment;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private boolean isDeleted;
    private Long parentId;
    private Long userId;
    private Long postId;
    private List<CommentResponse> reComments;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.content = comment.getContent();
        this.isDeleted = comment.isDeleted();
        this.parentId = comment.getParentId();
        this.userId = comment.getUserId();
        this.postId = comment.getPostId();
    }

    public CommentResponse(Comment comment, List<CommentResponse> reComments) {
        this(comment);
        this.reComments = reComments;
    }
}
