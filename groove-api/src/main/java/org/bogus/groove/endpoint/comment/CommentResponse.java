package org.bogus.groove.endpoint.comment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.domain.comment.CommentGetResult;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private Long parentId;
    private Long userId;
    private String nickName;
    private Long postId;
    private List<CommentResponse> reComments;

    public CommentResponse(CommentGetResult comment) {
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.content = comment.getContent();
        this.parentId = comment.getParentId();
        this.userId = comment.getUserId();
        this.nickName = comment.getNickName();
        this.postId = comment.getPostId();
        this.reComments = Optional.ofNullable(comment.getReComments()).orElseGet(Collections::emptyList).stream()
            .map(reComment -> new CommentResponse(reComment)).collect(
                Collectors.toList());
    }
}
