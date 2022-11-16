package org.bogus.groove.domain.report;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportCreator reportCreator;

    public void createReport(Long userId, Long postId, ReportTargetType reportTargetType, ReportReasonType reportReasonType) {
        reportCreator.createReport(userId, postId, reportTargetType, reportReasonType);
    }
}