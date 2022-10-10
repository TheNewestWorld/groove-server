package org.bogus.groove.endpoint.post;

import lombok.Getter;

@Getter
public class PostCreateRequest {
    private String title;
    private String content;
    private boolean isDeleted;
    private Long userId;
    private Long categoryId;
}
