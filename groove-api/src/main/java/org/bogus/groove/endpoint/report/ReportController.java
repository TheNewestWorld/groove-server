package org.bogus.groove.endpoint.report;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.domain.report.ReportService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/receive")
    public CommonResponse<Void> createReport(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                             @RequestBody ReportResponse reportResponse) {
        reportService.createReport(customUserDetails.getUserId(), reportResponse.getPostId(), reportResponse.getReportTargetType(),
            reportResponse.getReportReasonType());
        return CommonResponse.success();
    }

    @GetMapping("/{reportId}")
    public
}
