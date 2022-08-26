package org.bogus.groove.endpoint.recomment;

import lombok.Getter;

@Getter
public class ReCommentCreaetRequest {
    private String content;
    private Long userId;
    private Long commentId;
}
