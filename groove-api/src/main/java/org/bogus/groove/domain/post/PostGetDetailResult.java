package org.bogus.groove.domain.post;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostGetDetailResult {
    private PostGetResult post;
    private LocalDateTime createdAt;
    // TODO(nahyun.hong): Attachment가 추가되어야 합니다.

    public PostGetDetailResult(PostGetResult post, LocalDateTime createdAt) {
        this.post = post;
        this.createdAt = createdAt;
    }
}
