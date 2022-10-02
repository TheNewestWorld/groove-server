package org.bogus.groove.endpoint.post;

import java.util.List;
import lombok.Getter;
import org.bogus.groove.endpoint.comment.CommentResponse;

@Getter
public class PostDetailResponse {
    private PostResponse post;
    private List<CommentResponse> comments;

    public PostDetailResponse(PostResponse post, List<CommentResponse> comments) {
        this.post = post;
        this.comments = comments;
    }
}
