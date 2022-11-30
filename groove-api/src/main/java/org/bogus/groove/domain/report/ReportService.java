package org.bogus.groove.domain.report;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportCreator reportCreator;
    private final ReportReader reportReader;

    public void create(Long userId, Long postId, ReportTargetType reportTargetType, ReportReasonType reportReasonType) {
        reportCreator.createReport(userId, postId, reportTargetType, reportReasonType);
    }

    public List<Report> getList(Pageable pageable) {
        return reportReader.readAllReport(pageable);
    }

    public Report get(Long reportId) {
        return reportReader.readReport(reportId);
    }
}