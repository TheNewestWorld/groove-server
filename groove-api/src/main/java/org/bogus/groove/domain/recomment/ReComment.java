package org.bogus.groove.domain.recomment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ReComment {
    private Long id;
    private String content;
    private boolean isDeleted;
    private Long userId;
    private Long commentId;
}
