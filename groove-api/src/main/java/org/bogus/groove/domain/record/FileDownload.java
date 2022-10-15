package org.bogus.groove.domain.record;

import java.io.FileInputStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FileDownload {
    private final FileInputStream inputStream;
    private final String fileName;
}
