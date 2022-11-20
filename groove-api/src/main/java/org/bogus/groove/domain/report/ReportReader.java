package org.bogus.groove.domain.report;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.ReportRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportReader {
    private final ReportRepository reportRepository;

    public List<Report> readAllReport(Pageable pageable) {
        return reportRepository.findAll(pageable).stream().map(Report::new).collect(Collectors.toList());
    }

    public Report readReport(Long reportId) {
        var entity = reportRepository.findById(reportId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_REPORT));
        return new Report(entity);
    }

}
