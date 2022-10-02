package org.bogus.groove.endpoint.record;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.record.RecordService;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @Secured(SecurityCode.USER)
    @PostMapping(
        value = "/api/records",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CommonResponse<Void> record(
        @RequestParam MultipartFile record,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException {
        recordService.upload(userDetails.getUserId(), record);
        return CommonResponse.success();
    }
}
