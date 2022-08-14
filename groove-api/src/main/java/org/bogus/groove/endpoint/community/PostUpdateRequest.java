package org.bogus.groove.endpoint.community;

import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private String title;

    private String content;

    private boolean isTemporary;

    private Long categoryId;
}
