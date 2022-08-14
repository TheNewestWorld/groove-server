package org.bogus.groove.domain.community;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Comment {
    private Long id;

    private String content;

    private Long userId;

    private Long postId;
}
