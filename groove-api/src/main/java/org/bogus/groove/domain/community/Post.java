package org.bogus.groove.domain.community;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Post {
    private Long id;

    private String title;

    private String content;

    private Integer likeCount;

    private boolean isTemporary;

    private Long userId;

    private Long categoryId;
}
