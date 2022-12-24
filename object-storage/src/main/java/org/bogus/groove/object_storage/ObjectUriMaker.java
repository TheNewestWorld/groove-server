package org.bogus.groove.object_storage;

import org.bogus.groove.common.enumeration.AttachmentType;

public interface ObjectUriMaker {
    String make(AttachmentType attachmentType, String objectKey);
}
