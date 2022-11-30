package org.bogus.groove.endpoint.report;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.report.Report;
import org.bogus.groove.domain.report.ReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/reports")
    public CommonResponse<Void> createReport(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                             @RequestBody ReportRequestParam reportRequestParam) {
        reportService.create(customUserDetails.getUserId(), reportRequestParam.getPostId(),
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
    public CommonResponse<List<Report>> getReportList(Pageable pageable) {
        return CommonResponse.success(reportService.getList(pageable));
    }
}
