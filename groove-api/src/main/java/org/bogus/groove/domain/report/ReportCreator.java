package org.bogus.groove.domain.report;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;
import org.bogus.groove.storage.entity.ReportEntity;
import org.bogus.groove.storage.repository.ReportRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportCreator {
    private final ReportRepository reportRepository;

    public void createReport(Long userId, Long postId, ReportTargetType reportTargetType, ReportReasonType reportReasonType) {
        reportRepository.save(new ReportEntity(userId, postId, reportTargetType, reportReasonType));
    }
}