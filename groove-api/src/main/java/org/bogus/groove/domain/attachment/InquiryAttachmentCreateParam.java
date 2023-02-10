package org.bogus.groove.domain.attachment;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.AttachmentType;

@Getter
@AllArgsConstructor
@ToString
public class InquiryAttachmentCreateParam {
    private final InputStream inputStream;
    private final String fileName;
    private final long size;
    private final AttachmentType attachmentType;

}
