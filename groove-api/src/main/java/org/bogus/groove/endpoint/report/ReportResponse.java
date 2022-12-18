package org.bogus.groove.endpoint.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;

@Getter
@RequiredArgsConstructor
public class ReportResponse {
    private final Long reportId;
    private final Long userId;
    private final Long postId;
    private final ReportTargetType reportTargetType;
    private final ReportReasonType reportReasonType;
}
