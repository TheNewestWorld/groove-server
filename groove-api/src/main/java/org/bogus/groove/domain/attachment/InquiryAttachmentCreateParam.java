package org.bogus.groove.domain.attachment;

import java.io.IOException;
import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@ToString
public class InquiryAttachmentCreateParam {
    private final InputStream inputStream;
    private final String fileName;
    private final long size;
    private final AttachmentType attachmentType;

    public InquiryAttachmentCreateParam(MultipartFile multipartFile, AttachmentType attachmentType) throws IOException {
        this.inputStream = multipartFile.getInputStream();
        this.fileName = multipartFile.getName();
        this.size = multipartFile.getSize();
        this.attachmentType = attachmentType;

    }
}
