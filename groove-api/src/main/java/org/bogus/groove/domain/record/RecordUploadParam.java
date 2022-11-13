package org.bogus.groove.domain.record;

import java.io.InputStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecordUploadParam {
    private final InputStream inputStream;
    private final String fileName;
    private final long size;
}
