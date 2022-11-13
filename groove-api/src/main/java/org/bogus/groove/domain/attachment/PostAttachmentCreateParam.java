package org.bogus.groove.domain.attachment;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PostAttachmentCreateParam {
    private final InputStream inputStream;
    private final String fileName;
    private final long size;
}
