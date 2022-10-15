package org.bogus.groove.domain.record;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecordGetResult {
    private final long recordId;
    private final String recordName;
    private final LocalDateTime createdAt;
}
