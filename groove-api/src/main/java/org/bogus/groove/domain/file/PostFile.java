package org.bogus.groove.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PostFile {
    private Long id;

    private Long postId;

    private Long fileId;
}
