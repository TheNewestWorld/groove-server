package org.bogus.groove.domain.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.AttachmentType;

@Getter
@AllArgsConstructor
@ToString
public class Attachment {
    private Long id;

    private String path;

    private Integer size;

    private String extension;

    private AttachmentType fileType;
}
