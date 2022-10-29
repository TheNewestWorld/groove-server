package org.bogus.groove.domain.record;

import java.io.InputStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.object_storage.AttachmentUploadParam;

@Getter
@RequiredArgsConstructor
public class RecordUploadParam {
    private final InputStream inputStream;
    private final String fileName;
    private final long size;

    public AttachmentUploadParam toAttachmentUploadParam() {
        return new AttachmentUploadParam(
            this.getInputStream(),
            this.getFileName(),
            this.getSize(),
            AttachmentType.PRIVATE_RECORD
        );
    }
}
