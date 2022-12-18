package org.bogus.groove.endpoint.report;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.report.Report;
import org.bogus.groove.domain.report.ReportService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PostMapping("/reports")
    public CommonResponse<Void> createReport(@AuthenticationPrincipal GrooveUserDetails grooveUserDetails,
                                             @RequestBody ReportRequestParam reportRequestParam) {
        reportService.create(grooveUserDetails.getUserId(), reportRequestParam.getPostId(),
            reportRequestParam.getReportTargetType(),
            reportRequestParam.getReportReasonType());
        return CommonResponse.success();
    }

    @Secured(SecurityCode.ADMIN)
    @GetMapping("/report/{reportId}")
    public CommonResponse<Report> getReport(@PathVariable Long reportId) {
        return CommonResponse.success(reportService.get(reportId));
    }

    @Secured(SecurityCode.ADMIN)
    @GetMapping("/reports")
    public CommonResponse<PageResponse<List<ReportResponse>>> getReportList(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        var result = reportService.getList(pageRequest);
        return CommonResponse.success(new PageResponse<>(result.getNumber(), result.getSize(),
            result.map(
                (report) -> new ReportResponse(report.getId(), report.getUserId(), report.getTargetId(), report.getReportTargetType(),
                    report.getReportReasonType())).toList(), result.hasNext()));
    }
}
