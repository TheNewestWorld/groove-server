package org.bogus.groove.endpoint.post;

import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private String title;
    private String content;
    private Long categoryId;
}
