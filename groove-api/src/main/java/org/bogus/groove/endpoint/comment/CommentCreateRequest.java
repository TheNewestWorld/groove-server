package org.bogus.groove.endpoint.comment;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;
    private Long parentId;
}
