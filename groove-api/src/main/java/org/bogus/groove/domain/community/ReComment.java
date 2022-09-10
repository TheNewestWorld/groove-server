package org.bogus.groove.domain.community;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ReComment {
    private Long id;

    private String content;

    private Long userId;

    private Long commentId;
}