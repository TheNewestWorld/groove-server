package org.bogus.groove.domain.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;

@Getter
@RequiredArgsConstructor
public class ObjectUploadResult {
    private final String key;
    private final String path;
    private final long size;
    private final String fileName;
    private final String extension;
    private final AttachmentType type;
}
