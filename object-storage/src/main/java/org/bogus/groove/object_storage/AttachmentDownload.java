package org.bogus.groove.object_storage;

import java.io.InputStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AttachmentDownload {
    private final InputStream inputStream;
    private final String fileName;
}
