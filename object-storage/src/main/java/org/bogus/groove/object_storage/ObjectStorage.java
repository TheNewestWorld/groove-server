package org.bogus.groove.object_storage;

import java.io.InputStream;
import org.bogus.groove.common.enumeration.AttachmentType;

interface ObjectStorage {
    ObjectUploadResult upload(InputStream inputStream, AttachmentType attachmentType);

    InputStream download(String objectKey, AttachmentType attachmentType);
}
