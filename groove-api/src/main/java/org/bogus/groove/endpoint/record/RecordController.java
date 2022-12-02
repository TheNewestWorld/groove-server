package org.bogus.groove.endpoint.record;

import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.record.RecordService;
import org.bogus.groove.domain.record.RecordUploadParam;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @Operation(summary = "마이페이지 - 녹음파일 저장")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PostMapping(
        value = "/api/records",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CommonResponse<Void> record(
        @RequestPart MultipartFile record,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException {
        try (var input = record.getInputStream()) {
            recordService.upload(
                userDetails.getUserId(),
                new RecordUploadParam(
                    input,
                    "새로운 녹음",
                    record.getSize()
                )
            );
        }
        return CommonResponse.success();
    }

    @Operation(summary = "마이페이지 - 녹음파일 페이징")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
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
                        record.getId(),
                        record.getUri(),
                        record.getFileName(),
                        record.getCreatedAt()
                    )
                ).toList(),
                result.hasNext()
            )
        );
    }

    @Operation(summary = "마이페이지 - 녹음 삭제")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @DeleteMapping("/api/records/{recordId}")
    public CommonResponse<Void> deleteRecord(
        @PathVariable Long recordId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        recordService.delete(recordId, userDetails.getUserId());
        return CommonResponse.success();
    }

    @Operation(summary = "마이페이지 - 녹음 이름 변경")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PutMapping("/api/records/{recordId}")
    public CommonResponse<Void> updateRecordName(
        @PathVariable Long recordId,
        @RequestBody RecordNameUpdateRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        recordService.update(recordId, userDetails.getUserId(), request.getRecordName());
        return CommonResponse.success();
    }
}
