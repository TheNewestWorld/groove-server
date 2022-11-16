package org.bogus.groove.endpoint.report;

import lombok.Getter;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;

@Getter
public class ReportResponse {
    private Long userId;
    private Long postId;
    private ReportTargetType reportTargetType;
    private ReportReasonType reportReasonType;
}
