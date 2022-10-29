package org.bogus.groove.object_storage;

import java.io.FileInputStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AttachmentDownload {
    private final FileInputStream inputStream;
    private final String fileName;
}
