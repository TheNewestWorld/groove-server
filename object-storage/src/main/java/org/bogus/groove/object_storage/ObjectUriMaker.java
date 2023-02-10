package org.bogus.groove.object_storage;

import org.bogus.groove.common.enumeration.AttachmentType;

public interface ObjectUriMaker {
    String make(AttachmentType attachmentType, String objectKey);

    default String buildUri(String host, String... segments) {
        var uri = new StringBuilder(host);
        for (String segment : segments) {
            if (!segment.isBlank()) {
                uri.append("/").append(segment);
            }
        }
        return uri.toString();
    }
}
