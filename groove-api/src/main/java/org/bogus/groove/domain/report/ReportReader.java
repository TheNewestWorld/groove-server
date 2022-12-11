package org.bogus.groove.domain.report;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.ReportRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportReader {
    private final ReportRepository reportRepository;

    public Slice<Report> readAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reportRepository.reportAllPosts(pageable).map(Report::new);
    }

    public Report read(Long reportId) {
        var entity = reportRepository.findById(reportId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_REPORT));
        return new Report(entity);
    }

}
