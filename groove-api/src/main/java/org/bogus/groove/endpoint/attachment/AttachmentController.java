package org.bogus.groove.endpoint.attachment;

import io.swagger.v3.oas.annotations.Hidden;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.domain.attachment.AttachmentService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @Hidden
    @GetMapping("/attachments/{attachmentType}/{objectKey}")
    public ResponseEntity<StreamingResponseBody> download(
        @PathVariable AttachmentType attachmentType,
        @PathVariable String objectKey,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        var userId = userDetails == null ? null : userDetails.getUserId();
        var download = attachmentService.download(objectKey, attachmentType, userId);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(download.getFileName(), StandardCharsets.UTF_8)
                .build()
                .toString())
            .body(outputStream -> FileCopyUtils.copy(download.getInputStream(), outputStream));
    }
}
