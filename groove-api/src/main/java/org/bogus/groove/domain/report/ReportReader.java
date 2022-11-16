package org.bogus.groove.domain.report;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.ReportRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportReader {
    private final ReportRepository reportRepository;

    public Report read(Long id) {
        var entity = reportRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_REPORT));
        return new Report(entity);
    }

}
