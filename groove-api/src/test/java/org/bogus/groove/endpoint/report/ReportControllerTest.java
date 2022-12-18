/*
package org.bogus.groove.endpoint.report;

import static org.junit.jupiq
ter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;
import org.bogus.groove.domain.report.Report;
import org.bogus.groove.domain.report.ReportService;
import org.bogus.groove.storage.entity.ReportEntity;
import org.bogus.groove.storage.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReportControllerTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportController reportController;

    @Autowired
    private MockMvc mock;

    @Test
    void test() {

        List<ReportEntity> reportList =
            LongStream.range(0, 20).mapToObj(i -> new ReportEntity(i, i, ReportTargetType.POST, ReportReasonType.FALSE_INFORMATION))
                .collect(Collectors.toList());

        reportRepository.saveAll(reportList);

        PageRequest pageRequest = PageRequest.of(1, 10);

        Slice<Report> reportSlice = reportService.getList(pageRequest);
        assertEquals(10L, reportSlice.getSize());
        assertEquals(ReportReasonType.FALSE_INFORMATION, reportSlice.getContent().get(0).getReportReasonType());

        System.out.println("test");
    }
}
*/