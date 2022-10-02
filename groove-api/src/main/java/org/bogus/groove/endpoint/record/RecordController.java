package org.bogus.groove.endpoint.record;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.record.RecordService;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Secured(SecurityCode.USER)
    @GetMapping("/api/records")
    public CommonResponse<PageResponse<List<RecordGetResponse>>> getRecords(
        @RequestParam int page,
        @RequestParam int size,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        var result = recordService.getAll(userDetails.getUserId(), page, size);
        return CommonResponse.success(
            new PageResponse<>(
                result.getNumber(),
                result.getSize(),
                result.map((record) ->
                    new RecordGetResponse(
                        record.getRecordId(),
                        record.getRecordName(),
                        record.getCreatedAt()
                    )
                ).toList(),
                result.hasNext()
            )
        );
    }
}
