package org.bogus.groove.object_storage;

import java.io.InputStream;
import lombok.Getter;
import org.bogus.groove.common.enumeration.AttachmentType;

@Getter
public class AttachmentUploadParam {
    private final InputStream inputStream;
    private final String fileName;
    private final long size;
    private final long resourceId;
    private final AttachmentType attachmentType;
    private final String extension;

    public AttachmentUploadParam(InputStream inputStream, String fileName, long size, long resourceId, AttachmentType attachmentType) {
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.size = size;
        this.resourceId = resourceId;
        this.attachmentType = attachmentType;
        this.extension = fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
