package org.bogus.groove.object_storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;

@Getter
@RequiredArgsConstructor
public class AttachmentCreateParam {
    private final String objectKey;
    private final String path;
    private final String fileName;
    private final long size;
    private final Long resourceId;
    private final AttachmentType attachmentType;
}
