package org.bogus.groove.endpoint.record;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecordGetResponse {
    private final long recordId;
    private final String fileUri;
    private final String recordName;
    private final LocalDateTime createdAt;
}
