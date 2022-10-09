package org.bogus.groove.domain.post;

import lombok.Getter;

@Getter
public class PostGetDetailResult {
    private PostGetResult post;
    // TODO(nahyun.hong): Attachment가 추가되어야 합니다.

    public PostGetDetailResult(PostGetResult post) {
        this.post = post;
    }
}
