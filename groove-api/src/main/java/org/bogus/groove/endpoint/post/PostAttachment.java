package org.bogus.groove.endpoint.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PostAttachment {
    private MultipartFile file;

    @Schema(defaultValue = "POST_IMAGE", allowableValues = {"POST_IMAGE", "POST_RECORD"})
    private AttachmentType type;
}
