package org.bogus.groove.domain.report;

import lombok.Getter;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;
import org.bogus.groove.storage.entity.ReportEntity;

@Getter
public class Report {
    private final long id;
    private final long userId;
    private final long targetId;
    private final ReportTargetType reportTargetType;
    private final ReportReasonType reportReasonType;


    public Report(ReportEntity reportEntity) {
        this.id = reportEntity.getId();
        this.userId = reportEntity.getUserId();
        this.targetId = reportEntity.getTargetId();
        this.reportTargetType = reportEntity.getReportTargetType();
        this.reportReasonType = reportEntity.getReportReasonType();
    }
}
