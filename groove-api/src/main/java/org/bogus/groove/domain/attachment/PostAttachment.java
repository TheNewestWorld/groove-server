package org.bogus.groove.domain.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PostAttachment {
    private Long id;

    private Long postId;

    private Long fileId;
}
